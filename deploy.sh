echo "Iniciando despliegue"
#git pull

CID=$(docker ps -aqf "name=innova-pacs-server")
echo $CID

echo "Detendiendo contenedor"
docker stop $CID

echo "Borrando contenedor"
docker rm $CID

echo "Borrando imagen"
docker rmi innova-pacs-server:v1

echo "Creando jar de spring"
mvn clean package -DskipTests -Dmaven.test.skip=true
 
docker build -t innova-pacs-server:v1 .
docker run -p 8081:8080 --network dcm4chee_default --name innova-pacs-server -d innova-pacs-server:v1

echo "Terminando despliegue"
