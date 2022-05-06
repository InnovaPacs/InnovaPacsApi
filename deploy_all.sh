#!/bin/bash
echo '::::::: DEPLOYING BACKEND'
git pull

echo 'Configuring IP'
export HOST_IP=`hostname -I | awk '{print $1}'`
#echo 'export HOST_IP='$HOST_IP >> ~/.bashrc
#export PATH=$PATH:$HOST_IP

export DATA_BASE_URL=jdbc:postgresql://${HOST_IP}:5432/pacsdb
#echo 'export DATA_BASE_URL='$DATA_BASE_URL >> ~/.bashrc
#export PATH=$PATH:$DATA_BASE_URL

#~/.bashrc

#cat ~/.bashrc
#/opt/tomcat/bin/setenv.sh
#echo 'JAVA_OPTS=-DDATA_BASE_URL='$DATA_BASE_URL' -DHOST_IP='$HOST_IP
sudo echo 'JAVA_OPTS="-DDATA_BASE_URL='$DATA_BASE_URL' -DHOST_IP='$HOST_IP'"' > /opt/tomcat/bin/setenv.sh
#sudo echo ' -DHOST_IP='$HOST_IP >> /opt/tomcat/bin/setenv.sh

echo 'Creating war file'
sudo mvn -DdataBaseUrl=DEMO  install

echo 'Delete last war vertion'
sudo rm /opt/tomcat/webapps/innovaPacsApi.war

echo 'Moving war file on webapps directory'
sudo cp ./target/innovaPacsApi.war /opt/tomcat/webapps

echo '::::::: DEPLOYING FRONTEND'

cd .. 
cd InnovaPacsWeb
git pull

npm i

npm run build

npm run build:war

sudo rm /opt/tomcat/webapps/nnova-pacs-web.war

sudo cp ./dist/innova-pacs-web.war /opt/tomcat/webapps


echo 'Restart tomcat'
sudo systemctl restart tomcat

echo 'Deploy finished'