echo "Iniciando despliegue"
#git pull

CID=$(docker ps -aqf "name=innova-pacs-server-c1")
echo $CID

echo "Detendiendo contenedor"
docker stop $CID

echo "Borrando contenedor"
docker rm $CID

echo "Borrando imagen"
docker rmi innova-pacs-server-c1:v1

echo "Creando jar de spring"
mvn clean package -DskipTests -Dmaven.test.skip=true
 
docker build -t innova-pacs-server-c1:v1 .
docker run -p 8090:8090 --network dcm4chee_default --name innova-pacs-server-c1 -d innova-pacs-server-c1:v1

echo "Terminando despliegue"
