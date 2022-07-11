import cv2
from imutils.video import VideoStream
import time
import sys
import urllib3
import os
import threading
import base64


def object_detection():
    try:
        global fcount
        fcount = 0
        print("Starting Object Detection Mode")
        # start the video stream thread
        print("[INFO] starting video stream thread...")
        vs = VideoStream(src=0).start()
        # vs = VideoStream(usePiCamera=True).start()
        time.sleep(0.1)
        while True:

            frame = vs.read()

            # frame = imutils.resize(frame, width=450)
            body = detect_body(frame)
            if len(body) != 0:
                draw_rectangle(frame, body)
                out = str(len(body)) + " body detected successfully."
                print(out)
                fcount = fcount + 1
                if fcount > 5:
                    cv2.imwrite("emergency_imgs/emergency.png", frame)
                    break;
            time.sleep(0.1)

            face = detect_face(frame)
            if len(face) != 0:
                draw_rectangle(frame, face)
                out = str(len(face)) + " face detected successfully."
                print(out)
                fcount = fcount + 1
                if fcount > 5:
                    cv2.imwrite("emergency_imgs/emergency.png", frame)
                    break;
            time.sleep(0.1)

            cv2.imshow("Frame", frame)
            time.sleep(0.1)
            key = cv2.waitKey(1) & 0xFF

            # if the `q` key was pressed, break from the loop
            if key == ord("q"):
                break

        with open("emergency_imgs/emergency.png", "rb") as image_file:
            encoded_image = base64.b64encode(image_file.read())
            print(encoded_image)
            upload_server(encoded_image)

        cv2.destroyAllWindows()
        vs.stop()
    except Exception as e:
        print(e.message)


def upload_server(encoded_image):
    r = manager.request('POST', 'http://127.0.0.1/HSAD_webservices/test.php',
                        fields={'img': encoded_image, 'contact': '9140097080'})
    print(r.data)


def draw_rectangle(img, rects):
    for (x, y, w, h) in rects:
        cv2.rectangle(img, (x, y), (x + w, y + h), (0, 255, 0), 2)


def detect_body(img):
    # convert the test image to gray image as opencv face detector expects gray images
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    # load OpenCV face detector, I am using LBP which is fast
    # there is also a more accurate but slow Haar classifier
    body_cascade = cv2.CascadeClassifier('cascade_classifier/haarcascade_fullbody.xml')

    # let's detect multiscale (some images may be closer to camera than others) images
    # result is a list of faces
    body = body_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=2);
    # return only the face part of the image
    return body


def detect_face(img):
    # convert the test image to gray image as opencv face detector expects gray images
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    # load OpenCV face detector, I am using LBP which is fast
    # there is also a more accurate but slow Haar classifier
    face_cascade = cv2.CascadeClassifier('cascade_classifier/haarcascade_frontalface_alt.xml')

    # let's detect multiscale (some images may be closer to camera than others) images
    # result is a list of faces
    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5);

    # return only the face part of the image
    return faces


if __name__ == '__main__':
    manager = urllib3.PoolManager()  # http connectivity
    object_detection()
    """manager = urllib3.PoolManager()
    t1 = threading.Thread(target=object_detection, args=())
    t1.start()"""
