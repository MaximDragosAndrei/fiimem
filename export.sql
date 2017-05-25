
  CREATE OR REPLACE TRIGGER "FIIMEM"."PRIVACY_DELETE" 
         AFTER DELETE
            ON Privacy
            FOR EACH ROW
BEGIN
    DELETE FROM Privacy_Members
          WHERE Privacy_Members.pid =:old.pid;
    DELETE FROM Privacy_Files
          WHERE Privacy_Files.pid =:old.pid;
END;
ALTER TRIGGER "FIIMEM"."PRIVACY_DELETE" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."FILES_DELETE" 
         AFTER DELETE
            ON Files
            FOR EACH ROW
BEGIN
    DELETE FROM Privacy_Files
          WHERE Privacy_Files.fid =:old.fid;
    DELETE FROM Tags_Files
          WHERE Tags_Files.fid =:old.fid;
END;
ALTER TRIGGER "FIIMEM"."FILES_DELETE" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."TAGS_DELETE" 
         AFTER DELETE
            ON Tags
            FOR EACH ROW
BEGIN
    DELETE FROM Members_Tags
          WHERE Members_Tags.tid =:old.tid;
    DELETE FROM Tags_Files
          WHERE Tags_Files.tid =:old.tid;
END;
ALTER TRIGGER "FIIMEM"."TAGS_DELETE" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."POZEDELETE" 
after delete on Poze
for each row
begin
  delete from Files
  where format= :Old.format;
end;
ALTER TRIGGER "FIIMEM"."POZEDELETE" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."FILMEDELETE" 
after delete on Filme
for each row
begin
  delete from Files
  where format= :Old.format;
end;
ALTER TRIGGER "FIIMEM"."FILMEDELETE" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."DOCUMENTEDELETE" 
after delete on Documente
for each row
begin
  delete from Files
  where format= :Old.format;
end;
ALTER TRIGGER "FIIMEM"."DOCUMENTEDELETE" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."POZEUPDATE" 
after update on Poze
for each row
begin
  update Files
  set format = :New.format
  where address = :New.address and format = :Old.format;
end;
ALTER TRIGGER "FIIMEM"."POZEUPDATE" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."FILMEUPDATE" 
after update on Filme
for each row
begin
  update Files
  set format = :New.format
  where address = :New.address and format = :Old.format;
end;
ALTER TRIGGER "FIIMEM"."FILMEUPDATE" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."DOCUMENTEUPDATE" 
after update on Documente
for each row
begin
  update Files
  set format = :New.format
  where address = :New.address and format = :Old.format;
end;
ALTER TRIGGER "FIIMEM"."DOCUMENTEUPDATE" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."TADDRESS" 
  BEFORE INSERT OR UPDATE ON Members
  FOR EACH ROW
BEGIN
	IF( :new.fictiv = 1 and :new.deceaseddate is not null) THEN
		 RAISE_APPLICATION_ERROR( -20001,'Tii minte adresa cimitirului sau ce?');
	END IF;
END;
ALTER TRIGGER "FIIMEM"."TADDRESS" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."TLOG" 
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
ALTER TRIGGER "FIIMEM"."TLOG" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."TPASSWORD" 
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
ALTER TRIGGER "FIIMEM"."TPASSWORD" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."TEMAIL" 
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
ALTER TRIGGER "FIIMEM"."TEMAIL" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."TUSERNAME" 
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
ALTER TRIGGER "FIIMEM"."TUSERNAME" ENABLE

  CREATE OR REPLACE TRIGGER "FIIMEM"."MEMBERS_DELETE" 
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
ALTER TRIGGER "FIIMEM"."MEMBERS_DELETE" ENABLE

  CREATE TABLE "FIIMEM"."FILES" 
   (	"FID" NUMBER(10,0) NOT NULL ENABLE, 
	"MEMBERSMID" NUMBER(10,0) NOT NULL ENABLE, 
	"ADDRESS" VARCHAR2(255) NOT NULL ENABLE, 
	"NAME" VARCHAR2(255) NOT NULL ENABLE, 
	"FORMAT" VARCHAR2(16) NOT NULL ENABLE, 
	"FILES" BLOB NOT NULL ENABLE, 
	 PRIMARY KEY ("FID", "MEMBERSMID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 UNIQUE ("ADDRESS")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "owns" FOREIGN KEY ("MEMBERSMID")
	  REFERENCES "FIIMEM"."MEMBERS" ("MID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 
 LOB ("FILES") STORE AS BASICFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 

  CREATE TABLE "FIIMEM"."GENTREE" 
   (	"MID" NUMBER(10,0) NOT NULL ENABLE, 
	"MID2" NUMBER(10,0) NOT NULL ENABLE, 
	"RELATIONSHIP" NUMBER(1,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("MID", "MID2")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "belongs to" FOREIGN KEY ("MID2")
	  REFERENCES "FIIMEM"."MEMBERS" ("MID") ENABLE, 
	 CONSTRAINT "in" FOREIGN KEY ("MID")
	  REFERENCES "FIIMEM"."MEMBERS" ("MID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 

  CREATE TABLE "FIIMEM"."HISTORY" 
   (	"MID" NUMBER(10,0) NOT NULL ENABLE, 
	"HID" NUMBER(10,0) NOT NULL ENABLE, 
	"LOGINDATE" TIMESTAMP (9) WITH LOCAL TIME ZONE NOT NULL ENABLE, 
	"LOGOUTDATE" TIMESTAMP (9) WITH LOCAL TIME ZONE NOT NULL ENABLE, 
	 PRIMARY KEY ("MID", "HID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "has" FOREIGN KEY ("MID")
	  REFERENCES "FIIMEM"."MEMBERS" ("MID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 

  CREATE TABLE "FIIMEM"."MEMBERS" 
   (	"MID" NUMBER(10,0) NOT NULL ENABLE, 
	"SURNAME" VARCHAR2(255) NOT NULL ENABLE, 
	"FIRSTNAME" VARCHAR2(255) NOT NULL ENABLE, 
	"USERNAME" VARCHAR2(255), 
	"EMAIL" VARCHAR2(255), 
	"ADDRESS" VARCHAR2(255), 
	"PHONE" NUMBER(13,0), 
	"PASSWORD" VARCHAR2(255), 
	"FICTIV" NUMBER(1,0) DEFAULT 0 NOT NULL ENABLE, 
	"BITHDATE" TIMESTAMP (9) NOT NULL ENABLE, 
	"DECEASEDDATE" TIMESTAMP (9), 
	 PRIMARY KEY ("MID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 UNIQUE ("EMAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 

  CREATE TABLE "FIIMEM"."MEMBERS_TAGS" 
   (	"MID" NUMBER(10,0) NOT NULL ENABLE, 
	"TID" NUMBER(10,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("MID", "TID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "use" FOREIGN KEY ("MID")
	  REFERENCES "FIIMEM"."MEMBERS" ("MID") ENABLE, 
	 CONSTRAINT "used" FOREIGN KEY ("TID")
	  REFERENCES "FIIMEM"."TAGS" ("TID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 

  CREATE TABLE "FIIMEM"."PRIVACY" 
   (	"PID" NUMBER(10,0) NOT NULL ENABLE, 
	"RIGHTS" VARCHAR2(255) DEFAULT 1 NOT NULL ENABLE, 
	 PRIMARY KEY ("PID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 

  CREATE TABLE "FIIMEM"."PRIVACY_FILES" 
   (	"PID" NUMBER(10,0) NOT NULL ENABLE, 
	"FID" NUMBER(10,0) NOT NULL ENABLE, 
	"FILESMEMBERSMID" NUMBER(10,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("PID", "FID", "FILESMEMBERSMID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "allows" FOREIGN KEY ("FID", "FILESMEMBERSMID")
	  REFERENCES "FIIMEM"."FILES" ("FID", "MEMBERSMID") ENABLE, 
	 CONSTRAINT "granted to" FOREIGN KEY ("PID")
	  REFERENCES "FIIMEM"."PRIVACY" ("PID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 

  CREATE TABLE "FIIMEM"."PRIVACY_MEMBERS" 
   (	"PID" NUMBER(10,0) NOT NULL ENABLE, 
	"MID" NUMBER(10,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("PID", "MID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "grants" FOREIGN KEY ("PID")
	  REFERENCES "FIIMEM"."PRIVACY" ("PID") ENABLE, 
	 CONSTRAINT "share" FOREIGN KEY ("MID")
	  REFERENCES "FIIMEM"."MEMBERS" ("MID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 

  CREATE TABLE "FIIMEM"."TAGS" 
   (	"TID" NUMBER(10,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(16) NOT NULL ENABLE, 
	 PRIMARY KEY ("TID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 

  CREATE TABLE "FIIMEM"."TAGS_FILES" 
   (	"TID" NUMBER(10,0) NOT NULL ENABLE, 
	"FID" NUMBER(10,0) NOT NULL ENABLE, 
	"FILESMEMBERSMID" NUMBER(10,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("TID", "FID", "FILESMEMBERSMID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "contains" FOREIGN KEY ("FID", "FILESMEMBERSMID")
	  REFERENCES "FIIMEM"."FILES" ("FID", "MEMBERSMID") ENABLE, 
	 CONSTRAINT "describes" FOREIGN KEY ("TID")
	  REFERENCES "FIIMEM"."TAGS" ("TID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 

  CREATE TABLE "FIIMEM"."POZE" 
   (	"FID" NUMBER(10,0) NOT NULL ENABLE, 
	"MEMBERSMID" NUMBER(10,0) NOT NULL ENABLE, 
	"ADDRESS" VARCHAR2(255) NOT NULL ENABLE, 
	"NAME" VARCHAR2(255) NOT NULL ENABLE, 
	"FORMAT" VARCHAR2(16) NOT NULL ENABLE, 
	"FILES" BLOB NOT NULL ENABLE, 
	 PRIMARY KEY ("FID", "MEMBERSMID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 
 LOB ("FILES") STORE AS BASICFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 

  CREATE TABLE "FIIMEM"."FILME" 
   (	"FID" NUMBER(10,0) NOT NULL ENABLE, 
	"MEMBERSMID" NUMBER(10,0) NOT NULL ENABLE, 
	"ADDRESS" VARCHAR2(255) NOT NULL ENABLE, 
	"NAME" VARCHAR2(255) NOT NULL ENABLE, 
	"FORMAT" VARCHAR2(16) NOT NULL ENABLE, 
	"FILES" BLOB NOT NULL ENABLE, 
	 PRIMARY KEY ("FID", "MEMBERSMID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 
 LOB ("FILES") STORE AS BASICFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 

  CREATE TABLE "FIIMEM"."DOCUMENTE" 
   (	"FID" NUMBER(10,0) NOT NULL ENABLE, 
	"MEMBERSMID" NUMBER(10,0) NOT NULL ENABLE, 
	"ADDRESS" VARCHAR2(255) NOT NULL ENABLE, 
	"NAME" VARCHAR2(255) NOT NULL ENABLE, 
	"FORMAT" VARCHAR2(16) NOT NULL ENABLE, 
	"FILES" BLOB NOT NULL ENABLE, 
	 PRIMARY KEY ("FID", "MEMBERSMID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" 
 LOB ("FILES") STORE AS BASICFILE (
  TABLESPACE "USERS" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) 
