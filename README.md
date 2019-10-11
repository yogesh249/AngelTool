# Angel
This is a tool created to store problems and their solutions. This tool is written using Struts and it doesn't support storing attachments at present. 

##MyAngelStruts
Steps to deploy on your local machine.
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

mysql> insert into users values('amit@oberoiz.com','0cb1eb413b8f7cee17701a37a1d74dc3');
Query OK, 1 row affected (0.05 sec)

mysql> commit;
Query OK, 0 rows affected (0.00 sec)

Now you can login into your application.


Steps to deploy on AWS
1. Create an AWS Account
2. Go to Amazon RDS and create a database instance
3. Set the master username password as root and firewall
4. After you are done run the above queries to insert the records in users table. Make sure that you add "use angel" before each query.
5. After you are done, you need to allow access to database so that your application can talk to the database. There is a setting to allow this.
    a. Go to the database instance and click on it
    b. Go to Connectivity and security tab
    c. Under Security section, click on the link available
    d. Go to inbound tab and click on Edit
    e. Add an entry and allow the connections you want to allow. I've allowed all.
    
    
6. Go to Elastic Beanstalk
7. Update the hibernate.cfg.xml and give the hibernate connection url as some thing like - jdbc:mysql://angel.cluster-cimccxeslnzh.us-east-2.rds.amazonaws.com:3306/angel
3. Select tomcat as the server and upload the war file
4. Upload the deploy. 
5. Now you should be able to access the application.
