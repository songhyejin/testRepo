create table TPFUNDER(
   TPFID varchar2(20) primary key,
   TPFNAME varchar2(15) not null,
   TPFPASSWORD varchar2(15) not null,
   TPFBIRTH varchar2(10),
   TPFGENDER varchar2(2),
   TPFEMAIL varchar2(30),
   TPFZIPCODE varchar2(10) not null,
   TPFADDRESS varchar2(100) not null,
   TPFADDRESS_D varchar2(100),
   TPFPHONENUM number(13) not null,
   TPFQUALIFYTPPROPOSER varchar2(5) not null,
   TPFACCOUNTTYPE varchar2(2) not null
);

drop table TPFUNDER
drop table zipcode

create table zipcode(
	zipcode varchar2(7),
	sido varchar2(4),
	gugun varchar2(17),
	dong varchar2(26),
	ri varchar2(15),
	bldg varchar2(42),
	bunji varchar2(17),
	seq varchar2(5) primary key
);

load data infile 'c:\\zipcode.txt' into table zipcode

select TPFID, TPFNAME,  TPFPASSWORD, TPFBIRTH,  TPFGENDER, TPFEMAIL, TPFZIPCODE,TPFADDRESS,  TPFADDRESS_D, TPFPHONENUM,  TPFQUALIFYTPPROPOSER, TPFACCOUNTTYPE CUSTOMER from TPFUNDER  