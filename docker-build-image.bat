SET DOCKER_USER=vplatonov1
SET VERSION=0.1
docker build -t pet-adoption-system-back:%VERSION% .
docker push %DOCKER_USER%/pet-adoption-system-back:%VERSION%