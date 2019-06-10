FROM nginx
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 1235

# Start Nginx when the container has provisioned.
CMD ["nginx", "-g", "daemon off;"]