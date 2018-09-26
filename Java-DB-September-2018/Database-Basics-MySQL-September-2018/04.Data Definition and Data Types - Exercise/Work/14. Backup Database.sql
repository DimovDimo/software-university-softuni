#Backup database
mysqldump -u root -p soft_uni > softuni-backup.sql

#Restore database from mysql server
mysql -u root -p
create database soft_uni;
use soft_uni;
source softuni-backup.sql;

#Restore database outside mysql
mysql -u root -p soft_uni < softuni-backup.sql