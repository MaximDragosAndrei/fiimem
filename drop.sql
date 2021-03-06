--indecsi
drop index indexmulti;
drop index indexname;
drop index indexoras;
--triggere tabele
DROP TRIGGER Tpassword;
DROP TRIGGER Temail;
DROP TRIGGER Tusername;
DROP TRIGGER Taddress;
DROP TRIGGER Tlog;
DROP TRIGGER Members_Delete;
DROP TRIGGER Privacy_Delete;
DROP TRIGGER Files_Delete;
DROP TRIGGER Tags_Delete;
--view
DROP MATERIALIZED VIEW Poze;
DROP MATERIALIZED VIEW Filme;
DROP MATERIALIZED VIEW Documente;
--tabele
DROP TABLE Files CASCADE CONSTRAINTS;
DROP TABLE GenTree CASCADE CONSTRAINTS;
DROP TABLE History CASCADE CONSTRAINTS;
DROP TABLE Members CASCADE CONSTRAINTS;
DROP TABLE Members_Tags CASCADE CONSTRAINTS;
DROP TABLE Privacy CASCADE CONSTRAINTS;
DROP TABLE Privacy_Files CASCADE CONSTRAINTS;
DROP TABLE Privacy_Members CASCADE CONSTRAINTS;
DROP TABLE Tags CASCADE CONSTRAINTS;
DROP TABLE Tags_Files CASCADE CONSTRAINTS;
--secvente
drop sequence mid_seq;
drop sequence fid_seq;
