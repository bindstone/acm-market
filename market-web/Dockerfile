# BUILD STAGE
FROM node:16.13.0-alpine as build
WORKDIR /usr/local/app
COPY ./ /usr/local/app/

RUN npm install
RUN npm run build

# RUN STAGE
FROM nginx:latest
COPY --from=build /usr/local/app/dist/market-web /usr/share/nginx/html
