docker network create -d bridge tplb

docker build -t im-nginx-lb .

mkdir -p shared1 && mkdir -p shared2

echo "<h1>Hello 1</h1>" > shared1/index.html && echo "<h1>Hello 2</h1>" > shared2/index.html

docker run --rm \
    --mount type=bind,source=./shared1/,target=/usr/share/nginx/html \
    --network tplb -d \
    -p 81:80 \
    --name nginx1 nginx 
docker run --rm \
    --mount type=bind,source=./shared2/,target=/usr/share/nginx/html \
    --network tplb -d \
    -p 82:80 \
    --name nginx2 nginx 

docker run --rm \
    -d -p 83:80 \
    --network tplb \
    --name nginxlb im-nginx-lb 