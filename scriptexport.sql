set serveroutput on;
CREATE OR REPLACE DIRECTORY NUME AS 'C:\Users\Asus\Desktop\PSGBD proiect';
/
declare cursor output is select * from all_objects where object_type in ('TABLE','FUNCTION','PROCEDURE',
'VIEW','TRIGGER') AND owner='FIIMEM';
out_file utl_file.file_type;
buffer_output VARCHAR2(10000);
begin
out_file:=utl_file.fopen('NUME','exporttabele.sql','W');
FOR linie_output in output loop
buffer_output:=dbms_metadata.get_ddl(linie_output.object_type,linie_output.object_name);
utl_file.put_line(out_file,buffer_output);
end loop;
utl_file.fclose(out_file);
end;