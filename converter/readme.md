Project Intent -- 
To create myself a local running heic converter to jpeg
I have an iphone and was facing trouble with image conversion from heic format

-------------------------------------------------------------------------------------------
This Project - uses

step 1 - download this - magick

https://imagemagick.org/archive/binaries/ImageMagick-7.1.1-38-Q16-HDRI-x64-dll.exe  

this to be installed if in windows system

and check the version of the by ~ magick -version   in cmd

Step 2 -  Run the springboot application locally 

after then you can input your heic file using swagger and download response in jpeg

http://localhost:8080/swagger-ui/index.html#/


adding dockerfile so you can run the application in docker

make sure you have docker engine running.
docker run build -t <imageName> . 

last dot here tell the path of docker image

docker build -t converter .

docker run -p 8080:8080 your-app-image

docker run -p 8080:8080 converter


