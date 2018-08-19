1. Install Tomcat on your machine.
2. Open tomat manager, and deploy the war file MyAngelStruts.war
3. Install MySQL 5.5 version (Do not install 8.0 version, it gives some connection problem).
4. Run the following sql queries

mysql> create database angel;
Query OK, 1 row affected (0.00 sec)

mysql> use angel;
Database changed

mysql> create table users(username varchar(100), password varchar(256));
Query OK, 0 rows affected (0.13 sec)

mysql> create table angel(solutionno int, description varchar(4000), problem varchar(4000), solution varchar(4000), logs varchar(4000), submittedby varchar(400));
Query OK, 0 rows affected (0.16 sec)
// The password is amit
mysql> insert into users('amit@oberoiz.com','0cb1eb413b8f7cee17701a37a1d74dc3');
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near ''amit@oberoiz.com','0cb1eb413b8f7cee17701a37a1d74dc3')' at line 1
mysql> insert into users values('amit@oberoiz.com','0cb1eb413b8f7cee17701a37a1d74dc3');
Query OK, 1 row affected (0.05 sec)

mysql> commit;
Query OK, 0 rows affected (0.00 sec)

Now you can login into your application.