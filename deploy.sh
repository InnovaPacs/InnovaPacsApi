echo "Iniciando despliegue"
#git pull

CID=$(docker ps -aqf "name=innova-pacs-demo-server")
echo $CID

echo "Detendiendo contenedor"
docker stop $CID

echo "Borrando contenedor"
docker rm $CID

echo "Borrando imagen"
docker rmi innova-pacs-demo-server:v1

echo "Creando jar de spring"
mvn clean package -DskipTests -Dmaven.test.skip=true
 
docker build -t innova-pacs-demo-server:v1 .
docker run -p 8090:8090 --network dcm4chee --name innova-pacs-demo-server -d innova-pacs-demo-server:v1
echo "Terminando despliegue"
