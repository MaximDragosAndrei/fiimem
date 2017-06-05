--tabele
CREATE TABLE Files (fid number(10) NOT NULL, Membersmid number(10) NOT NULL, address varchar2(255) NOT NULL UNIQUE, name varchar2(255) NOT NULL, format varchar2(16) NOT NULL,files blob NOT NULL, PRIMARY KEY (fid, Membersmid));
CREATE TABLE GenTree (mid number(10) NOT NULL, mid2 number(10) NOT NULL, relationship number(1) NOT NULL, PRIMARY KEY (mid, mid2));
CREATE TABLE History (mid number(10) NOT NULL, hid number(10) NOT NULL, logindate timestamp(9) with local time zone NOT NULL , logoutdate timestamp(9) with local time zone NOT NULL , PRIMARY KEY (mid, hid));
CREATE TABLE Members (mid number(10) NOT NULL, surname varchar2(255) NOT NULL, firstname varchar2(255) NOT NULL, username varchar2(255), email varchar2(255) UNIQUE, address varchar2(255), phone varchar2(18), password varchar2(255), fictiv number(1) DEFAULT 0 NOT NULL, bithdate timestamp(9) NOT NULL, deceaseddate timestamp(9), PRIMARY KEY (mid));
CREATE TABLE Members_Tags (mid number(10) NOT NULL, tid number(10) NOT NULL, PRIMARY KEY (mid, tid));
CREATE TABLE Privacy (pid number(10) NOT NULL, rights varchar2(255) DEFAULT 1 NOT NULL, PRIMARY KEY (pid));
CREATE TABLE Privacy_Files (pid number(10) NOT NULL, fid number(10) NOT NULL, FilesMembersmid number(10) NOT NULL, PRIMARY KEY (pid, fid, FilesMembersmid));
CREATE TABLE Privacy_Members (pid number(10) NOT NULL, mid number(10) NOT NULL, PRIMARY KEY (pid, mid));
CREATE TABLE Tags (tid number(10) NOT NULL, name varchar2(16) NOT NULL, PRIMARY KEY (tid));
CREATE TABLE Tags_Files (tid number(10) NOT NULL, fid number(10) NOT NULL, FilesMembersmid number(10) NOT NULL, PRIMARY KEY (tid, fid, FilesMembersmid));
CREATE UNIQUE INDEX Files_fid ON Files (fid);
CREATE UNIQUE INDEX History_hid ON History (hid);
CREATE INDEX Tags_name ON Tags (name);
ALTER TABLE Privacy_Files ADD CONSTRAINT "allows" FOREIGN KEY (fid, FilesMembersmid) REFERENCES Files (fid, Membersmid);
ALTER TABLE GenTree ADD CONSTRAINT "belongs to" FOREIGN KEY (mid2) REFERENCES Members (mid);
ALTER TABLE Tags_Files ADD CONSTRAINT "contains" FOREIGN KEY (fid, FilesMembersmid) REFERENCES Files (fid, Membersmid);
ALTER TABLE Tags_Files ADD CONSTRAINT "describes" FOREIGN KEY (tid) REFERENCES Tags (tid);
ALTER TABLE Privacy_Files ADD CONSTRAINT "granted to" FOREIGN KEY (pid) REFERENCES Privacy (pid);
ALTER TABLE Privacy_Members ADD CONSTRAINT "grants" FOREIGN KEY (pid) REFERENCES Privacy (pid);
ALTER TABLE History ADD CONSTRAINT "has" FOREIGN KEY (mid) REFERENCES Members (mid);
ALTER TABLE GenTree ADD CONSTRAINT "in" FOREIGN KEY (mid) REFERENCES Members (mid);
ALTER TABLE Files ADD CONSTRAINT "owns" FOREIGN KEY (Membersmid) REFERENCES Members (mid);
ALTER TABLE Privacy_Members ADD CONSTRAINT "share" FOREIGN KEY (mid) REFERENCES Members (mid);
ALTER TABLE Members_Tags ADD CONSTRAINT "use" FOREIGN KEY (mid) REFERENCES Members (mid);
ALTER TABLE Members_Tags ADD CONSTRAINT "used" FOREIGN KEY (tid) REFERENCES Tags (tid);
--triggere
CREATE OR REPLACE TRIGGER Taddress
  BEFORE INSERT OR UPDATE ON Members
  FOR EACH ROW
BEGIN
	IF( :new.fictiv = 1 and :new.deceaseddate is not null) THEN
		 RAISE_APPLICATION_ERROR( -20001,'Tii minte adresa cimitirului sau ce?');
	END IF;
END;
/
CREATE OR REPLACE TRIGGER Tlog
  BEFORE INSERT OR UPDATE ON History
  FOR EACH ROW
BEGIN
	IF( :new.logindate > :new.logoutdate)THEN
		RAISE_APPLICATION_ERROR( -20001,'bine ca te poti deconecta in trecut');
		ELSE IF( :new.logindate > SYSDATE)THEN
					RAISE_APPLICATION_ERROR( -20001,'bine ca te poti loga in viitor');
			END IF;
			IF( :new.logoutdate > SYSDATE)THEN
				RAISE_APPLICATION_ERROR( -20001,'bine ca te poti deloga in viitor');
			END IF;
	END IF;
END;
/
CREATE OR REPLACE TRIGGER Tpassword
  BEFORE INSERT OR UPDATE ON Members
  FOR EACH ROW
BEGIN
  IF( :new.password is not null and :new.fictiv = 1 )
  THEN
    RAISE_APPLICATION_ERROR( -20001, 'Bunicu are paola' );
  END IF;
  IF(:new.password is null and :new.fictiv = 0) THEN
    RAISE_APPLICATION_ERROR( -20001, 'Asta are nevoie de parola' );
  END IF;
END;
/
CREATE OR REPLACE TRIGGER tEMAIL
  BEFORE INSERT OR UPDATE ON Members
  FOR EACH ROW
BEGIN
IF( :new.fictiv = 0) THEN
    IF( :new.email is null  )
    THEN
      RAISE_APPLICATION_ERROR( -20001, 'Ce facem aici? Utilizatoi fara parola! Alo!' );
     ELSE
    IF( :new.email not like '%_@_%') THEN
       RAISE_APPLICATION_ERROR( -20001, 'Macar de ar fi valid.' );
    END IF;
  END IF;
END IF;
 IF( :new.email is not null and :new.fictiv = 1 )
  THEN
    RAISE_APPLICATION_ERROR( -20001, 'Bine ca ii punem lui stra-strabunica email' );
  END IF;
END;
/
CREATE OR REPLACE TRIGGER Tusername
  BEFORE INSERT OR UPDATE ON Members
  FOR EACH ROW
BEGIN
  IF( :new.fictiv = 1) THEN
    IF( :new.username is not null) THEN
    RAISE_APPLICATION_ERROR( -20002,'Tocmai i.am facut cont lui bunica!');
    END IF;
  END IF;
  IF( :new.username is null and :new.fictiv = 0)THEN
    RAISE_APPLICATION_ERROR( -20002,'Eu te credeam inteligent(a). Ai nevoie de un username!');
  END IF;
END;
/
 CREATE or replace TRIGGER Members_Delete
         AFTER DELETE
            ON Members
            For each row
BEGIN
         DELETE FROM GenTree
        	WHERE GenTree.mid = :old.mid;
        DELETE FROM Privacy_Members
        	WHERE Privacy_Members.mid = :old.mid;
        DELETE FROM Members_Tags
        	WHERE Members_Tags.mid = :old.mid;
	DELETE FROM Files
		WHERE Files.Membersmid=:old.mid;
	DELETE FROM Privacy_Files
		WHERE Privacy_Files.FilesMembersmid=:old.mid;
	DELETE FROM History
		WHERE History.mid=:old.mid;
	DELETE FROM Tags_Files
		WHERE Tags_Files.FilesMembersmid=:old.mid;
END;
/
  CREATE OR REPLACE TRIGGER Privacy_Delete
         AFTER DELETE
            ON Privacy
            FOR EACH ROW
BEGIN
    DELETE FROM Privacy_Members
          WHERE Privacy_Members.pid =:old.pid;
    DELETE FROM Privacy_Files
          WHERE Privacy_Files.pid =:old.pid;
END;
/
 
  CREATE OR REPLACE TRIGGER Files_Delete
         AFTER DELETE
            ON Files
            FOR EACH ROW
BEGIN
    DELETE FROM Privacy_Files
          WHERE Privacy_Files.fid =:old.fid;
    DELETE FROM Tags_Files
          WHERE Tags_Files.fid =:old.fid;
END;
/
  CREATE OR REPLACE TRIGGER Tags_Delete
         AFTER DELETE
            ON Tags
            FOR EACH ROW
BEGIN
    DELETE FROM Members_Tags
          WHERE Members_Tags.tid =:old.tid;
    DELETE FROM Tags_Files
          WHERE Tags_Files.tid =:old.tid;
END;
/
--view
create materialized view poze for update
as
select * from files where format in('.jpg','.gif','.tiff','.tga','.psd','.png','.bmp','.wmf');
/
create materialized view filme for update
as
select * from files where format in('.mp3','.mp4','.mpg','.mpeg','.3gp','.mov','.asf','.wmv');
/
create materialized view documente for update
as
select * from files where format in('.doc','.txt','.rtf','.xls','.xlm','.DBF','.MSG','.PPT','.PPS');
/
--indecsi
create index indexmulti on Members(surname, address);
/
create index indexname on Members(upper(username));
/
create index indexoras on Members(INITCAP(LOWER(regexp_substr(address,'[^,]+'))));
/
create  sequence mid_seq
MINVALUE 10000 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 10220 CACHE 200 ;
