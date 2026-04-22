CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `email_address` varchar(255) DEFAULT NULL,
                         `home_address` varchar(255) DEFAULT NULL,
                         `mobile_number` varchar(255) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `nic_number` varchar(255) DEFAULT NULL,
                         `photo_url` varchar(255) DEFAULT NULL,
                         `user_role` enum('ADMIN','CUSTOMER','OWNER') DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `categories` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `description` varchar(255) DEFAULT NULL,
                              `name` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `items` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `image_url` varchar(255) DEFAULT NULL,
                         `item_availability` enum('AVAILABLE','NOT_AVAILABLE') DEFAULT NULL,
                         `item_name` varchar(255) DEFAULT NULL,
                         `location` varchar(255) DEFAULT NULL,
                         `price_per_day` double DEFAULT NULL,
                         `category_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FKjcdcde7htb3tyjgouo4g9xbmr` (`category_id`),
                         CONSTRAINT `FKjcdcde7htb3tyjgouo4g9xbmr` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookings` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `booking_payment_status` enum('PAID','UNPAID') DEFAULT NULL,
                            `created_at` datetime(6) DEFAULT NULL,
                            `end_date` date DEFAULT NULL,
                            `start_date` date DEFAULT NULL,
                            `status` enum('CANCELLED','CONFIRMED','PENDING') DEFAULT NULL,
                            `total_days` int DEFAULT NULL,
                            `total_price` double DEFAULT NULL,
                            `updated_at` datetime(6) DEFAULT NULL,
                            `customer_id` bigint DEFAULT NULL,
                            `item_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKib6gjgj2e9binkktxmm175bmm` (`customer_id`),
                            KEY `FKbtv44e8p4a4pq8hfuakjbtfpc` (`item_id`),
                            CONSTRAINT `FKbtv44e8p4a4pq8hfuakjbtfpc` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`),
                            CONSTRAINT `FKib6gjgj2e9binkktxmm175bmm` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `payments` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `amount` double DEFAULT NULL,
                            `created_at` datetime(6) DEFAULT NULL,
                            `payment_method` enum('CARD','PAYPAL') DEFAULT NULL,
                            `payment_status` enum('FAILED','PENDING','SUCCESS') DEFAULT NULL,
                            `transaction_id` varchar(255) DEFAULT NULL,
                            `booking_id` bigint DEFAULT NULL,
                            `customer_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UKnuscjm6x127hkb15kcb8n56wo` (`booking_id`),
                            KEY `FKd1qot1f3alweegm6ledjow6nj` (`customer_id`),
                            CONSTRAINT `FKc52o2b1jkxttngufqp3t7jr3h` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`),
                            CONSTRAINT `FKd1qot1f3alweegm6ledjow6nj` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `reviews` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `comment` varchar(255) DEFAULT NULL,
                           `rating` int DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `item_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`),
                           KEY `FKd0qivr20lp2u34cfcrr0ibct7` (`item_id`),
                           CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                           CONSTRAINT `FKd0qivr20lp2u34cfcrr0ibct7` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `owner_item` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `item_id` bigint DEFAULT NULL,
                              `owner_id` bigint DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FK7cl1uv3ok87a5xi1w073vvtci` (`item_id`),
                              KEY `FKhf1n5nonuv5bumhyb44r5u4pa` (`owner_id`),
                              CONSTRAINT `FK7cl1uv3ok87a5xi1w073vvtci` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`),
                              CONSTRAINT `FKhf1n5nonuv5bumhyb44r5u4pa` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;