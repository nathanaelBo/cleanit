# Use latest node alpine image as build image
FROM node:alpine AS build

WORKDIR /app

# Copy the working directory in the container
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy everything to the container
COPY . .

# Accept build argument and set environment variable
ARG VITE_API_BASE_URL
ENV VITE_API_BASE_URL $VITE_API_BASE_URL

# Build the project
RUN npm run build

# Use nginx alpine image as production image
FROM nginx:alpine as runner

# Copy the build files to nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Add a custom nginx configuration file
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Start NGINX as foreground process
CMD ["nginx", "-g", "daemon off;"]