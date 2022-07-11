from Tkinter import * #its used to design user interface
import Tkinter
from imutils.video import VideoStream  #camera interfacing and reading frames
import time #if wants to perorm some delay
import cv2 #image processing library
import threading #multi threading
from PIL import Image,ImageTk #image formation purpose

def vp_start_gui():
    '''Starting point when module is the main routine.'''
    global val, w, root
    root = Tk()
    top = crowd_counting(root)
    root.mainloop()

class crowd_counting:
    def __init__(self, top=None):
        '''This class configures and populates the toplevel window.
           top is the toplevel containing window.'''
        _bgcolor = '#d9d9d9'  # X11 color: 'gray85'
        _fgcolor = '#000000'  # X11 color: 'black'
        _compcolor = '#d9d9d9' # X11 color: 'gray85'
        _ana1color = '#d9d9d9' # X11 color: 'gray85'
        _ana2color = '#d9d9d9' # X11 color: 'gray85'
        font10 = "-family {Segoe UI} -size 9 -weight bold -slant roman"  \
            " -underline 0 -overstrike 0"
        font12 = "-family {Segoe UI} -size 23 -weight bold -slant "  \
            "roman -underline 0 -overstrike 0"
        font13 = "-family {Segoe UI} -size 16 -weight bold -slant "  \
            "roman -underline 0 -overstrike 0"
        font16 = "-family {Segoe UI} -size 12 -weight bold -slant "  \
            "roman -underline 0 -overstrike 0"
        font9 = "-family {Segoe UI} -size 11 -weight bold -slant roman"  \
            " -underline 0 -overstrike 0"

        top.geometry("1453x732+207+87")
        top.title("Crowd Counting")
        top.configure(background="#8080c0")



        self.menubar = Menu(top,font="TkMenuFont",bg=_bgcolor,fg=_fgcolor)
        top.configure(menu = self.menubar)



        self.Frame1 = Frame(top)
        self.Frame1.place(relx=0.03, rely=0.16, relheight=0.77, relwidth=0.6)
        self.Frame1.configure(relief=GROOVE)
        self.Frame1.configure(borderwidth="2")
        self.Frame1.configure(relief=GROOVE)
        self.Frame1.configure(background="#d9d9d9")
        self.Frame1.configure(width=865)

        self.Label1 = Label(self.Frame1)
        self.Label1.place(relx=0.01, rely=0.04, height=521, width=837)
        self.Label1.configure(background="#d9d9d9")
        self.Label1.configure(disabledforeground="#a3a3a3")
        self.Label1.configure(foreground="#000000")
        self.Label1.configure(width=837)

        self.Label2 = Label(top)
        self.Label2.place(relx=-0.01, rely=0.1, height=31, width=267)
        self.Label2.configure(background="#8080c0")
        self.Label2.configure(disabledforeground="#a3a3a3")
        self.Label2.configure(font=font9)
        self.Label2.configure(foreground="#ffffff")
        self.Label2.configure(text='''ROI MODE''')
        self.Label2.configure(width=267)







        self.Button1 = Button(top)
        self.Button1.place(relx=0.68, rely=0.74, height=62, width=238)
        self.Button1.configure(activebackground="#d9d9d9")
        self.Button1.configure(activeforeground="#000000")
        self.Button1.configure(background="#d9d9d9")
        self.Button1.configure(disabledforeground="#a3a3a3")
        self.Button1.configure(font=font10)
        self.Button1.configure(foreground="#000000")
        self.Button1.configure(highlightbackground="#d9d9d9")
        self.Button1.configure(highlightcolor="black")
        self.Button1.configure(pady="0")
        self.Button1.configure(text='''START ROI''')
        self.Button1.configure(width=238)
        self.Button1.configure(command=self.object1)


        self.Label7 = Label(top)
        self.Label7.place(relx=0.29, rely=0.1, height=31, width=477)
        self.Label7.configure(background="#8080c0")
        self.Label7.configure(disabledforeground="#a3a3a3")
        self.Label7.configure(font=font16)
        self.Label7.configure(foreground="#ffff00")
        self.Label7.configure(text='''ROI''')
        self.Label7.configure(width=477)


    def objects(self):
        print("object")
        try:
            t1=threading.Thread(target=self.object, args=()).start()
        except Exception as e:
            print(e.message)


    #pich photo from system
    def object1(self):

        frame = cv2.imread("imgface.jpg")
        face = self.detect_face(frame)

        if(len(face) != 0):
            print("hello")
            self.draw_rectangle(frame, face)
            out = str(len(face)) + " face detected successfully."
            self.Label7.config(text=out)
            # self.say(out)
            b, g, r = cv2.split(frame)
            frame = cv2.merge((r, g, b))
            im = Image.fromarray(frame)
            logo = ImageTk.PhotoImage(image=im)
            self.Label1.configure(compound=Tkinter.CENTER, image=logo)
            self.Label1.image = logo

    #camera photo reading
    def object(self):
        print("hello")
        try:
            print("Starting Object Detection Mode")
            # start the video stream thread
            print("[INFO] starting video stream thread...")
            vs = VideoStream(src=0).start()
            # vs = VideoStream(usePiCamera=True).start()
            time.sleep(1)
            while True:
                frame = vs.read()
                # frame = imutils.resize(frame, width=450)
                face = self.detect_face(frame)
                if (len(face) != 0):
                    self.draw_rectangle(frame,face)
                    out = str(len(face)) + " face detected successfully."
                    self.Label7.config(text=out)
                    #self.say(out)
                    b, g, r = cv2.split(frame)
                    frame= cv2.merge((r, g, b))
                    im = Image.fromarray(frame)
                    logo = ImageTk.PhotoImage(image=im)
                    self.Label1.configure(compound=Tkinter.CENTER, image=logo)
                    self.Label1.image = logo
                    #self.Label1.after(200, self.test)
                    time.sleep(1)
            vs.stop()
        except Exception as e:
            print(e.message)




    def draw_rectangle(self,img, rects):
        for (x, y, w, h) in rects:
            cv2.rectangle(img, (x, y), (x + w, y + h), (0, 0, 255), 2)

    def detect_face(self,img):
        # convert the test image to gray image as opencv face detector expects gray images
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

        # load OpenCV face detector, I am using LBP which is fast
        # there is also a more accurate but slow Haar classifier
        face_cascade = cv2.CascadeClassifier('cascade_classifiers/haarcascade_frontalface_alt.xml')

        # let's detect multiscale (some images may be closer to camera than others) images
        # result is a list of faces
        faces = face_cascade.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=5);
        # return only the face part of the image
        return faces



if __name__ == '__main__':

    vp_start_gui()