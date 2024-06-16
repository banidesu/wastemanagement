# Gunakan PHP-FPM 8.1 berbasis Alpine
FROM php:8.1-fpm-alpine

# Install dependencies yang diperlukan termasuk pdo_mysql dan nginx
RUN apk add --no-cache nginx wget libpng-dev libjpeg-turbo-dev freetype-dev oniguruma-dev libzip-dev \
    && docker-php-ext-configure gd --with-freetype --with-jpeg \
    && docker-php-ext-install pdo_mysql mbstring exif pcntl bcmath gd zip

# Buat direktori yang diperlukan untuk nginx
RUN mkdir -p /run/nginx /var/www/html

# Salin konfigurasi nginx
COPY docker/nginx.conf /etc/nginx/nginx.conf

# Set up aplikasi
WORKDIR /var/www/html
COPY . /var/www/html
COPY ./src /var/www/html/src

# Install Composer
RUN wget http://getcomposer.org/composer.phar && \
    chmod a+x composer.phar && \
    mv composer.phar /usr/local/bin/composer

# Install dependencies aplikasi Laravel
RUN composer install --no-dev

# Atur kepemilikan direktori aplikasi
RUN chown -R www-data:www-data /var/www/html

# Expose port untuk nginx
EXPOSE 80

# Salin script startup
COPY docker/startup.sh /usr/local/bin/startup.sh
RUN chmod +x /usr/local/bin/startup.sh

# Jalankan script startup saat container dimulai
CMD ["sh", "/usr/local/bin/startup.sh"]