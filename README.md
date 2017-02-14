# File-Upload-And-Download

Database Create:
  create database fileupload;
  
 Using Database:
   use fileupload;
   
   Create Table:
      CREATE TABLE `images` (
  `imageId` int(11) NOT NULL auto_increment,
  `image` mediumblob,
  PRIMARY KEY  (`imageId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
  
  
