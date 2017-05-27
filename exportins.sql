CREATE or replace DIRECTORY NUME as 'C:\Users\Asus\Desktop\PSGBD proiect';
GRANT READ ON DIRECTORY NUME TO PUBLIC;

set serveroutput on;

declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_mid number(10,0);
v_surname varchar2(200);
v_firstname varchar2(200);
v_username varchar2(200);
v_email varchar2(200);
v_address varchar2(200);
v_password varchar2(200);
v_NUMRows integer;
v_count integer:=1;
v_phone number(13,0);
v_fictiv number(1,0);
v_bithdate timestamp(9);
v_deceaseddate timestamp(9);
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','export.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'MEMBERS') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select mid from Members order by mid asc) loop
       stmt_str := 'select * from Members where mid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.mid);
       dbms_sql.define_column(v_CursorID, 1, v_mid);
       dbms_sql.define_column(v_CursorID, 2, v_surname,200);
       dbms_sql.define_column(v_CursorID, 3, v_firstname, 200);
       dbms_sql.define_column(v_CursorID, 4, v_username, 200);
       dbms_sql.define_column(v_CursorID, 5, v_email,200);
       dbms_sql.define_column(v_CursorID, 6, v_address, 200);
       dbms_sql.define_column(v_CursorID, 7, v_phone);
       dbms_sql.define_column(v_CursorID, 8, v_password,13); 
       dbms_sql.define_column(v_CursorID, 9, v_fictiv); 
       dbms_sql.define_column(v_CursorID, 10, v_bithdate); 
       dbms_sql.define_column(v_CursorID, 11, v_deceaseddate); 
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_mid);
            dbms_sql.column_value(v_CursorID,2,v_surname);
            dbms_sql.column_value(v_CursorID,3,v_firstname);
            dbms_sql.column_value(v_CursorID,4,v_username);
            dbms_sql.column_value(v_CursorID,5,v_email);
            dbms_sql.column_value(v_CursorID,6,v_address);
            dbms_sql.column_value(v_CursorID,7,v_phone);
            dbms_sql.column_value(v_CursorID,8,v_password);
            dbms_sql.column_value(v_CursorID,9,v_fictiv);
            dbms_sql.column_value(v_CursorID,10,v_bithdate);
            dbms_sql.column_value(v_CursorID,11,v_deceaseddate);
            
            v_surname := chr(39)||v_surname||chr(39);
            v_firstname := chr(39)||v_firstname||chr(39);           
            v_username :=chr(39)||v_username||chr(39);
            v_email := chr(39)||v_email||chr(39);
            v_address := chr(39)||v_address||chr(39);           
            v_password :=chr(39)||v_password||chr(39);
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_mid||', '||v_surname||', '||v_firstname||', '||v_username||', '||v_email||', '||v_address||', '||v_phone||', '||v_password||', '||v_fictiv||', '||v_bithdate||', '||v_deceaseddate||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/
set SERVEROUTPUT ON;
declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_mid number(10,0);
v_mid2 number(10,0);
v_relationship number(1,0);
v_NUMRows integer;
v_count integer:=1;
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','export1.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'GENTREE') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select mid from GENTREE order by mid asc) loop
       stmt_str := 'select * from Gentree where mid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.mid);
       dbms_sql.define_column(v_CursorID, 1, v_mid);
       dbms_sql.define_column(v_CursorID, 2, v_mid2);
       dbms_sql.define_column(v_CursorID, 3, v_relationship);
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_mid);
            dbms_sql.column_value(v_CursorID,2,v_mid2);
            dbms_sql.column_value(v_CursorID,3,v_relationship);
            
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_mid||', '||v_mid2||', '||v_relationship||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/
set SERVEROUTPUT ON;
declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_mid number(10,0);
v_hid number(10,0);
v_logindate timestamp(9);
v_logoutdate timestamp(9);
v_NUMRows integer;
v_count integer:=1;
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','export2.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'HISTORY') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select mid from History order by mid asc) loop
       stmt_str := 'select * from HISTORY where mid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.mid);
       dbms_sql.define_column(v_CursorID, 1, v_mid);
       dbms_sql.define_column(v_CursorID, 2, v_hid);
       dbms_sql.define_column(v_CursorID, 3, v_logindate);
       dbms_sql.define_column(v_CursorID, 4, v_logoutdate);
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_mid);
            dbms_sql.column_value(v_CursorID,2,v_hid);
            dbms_sql.column_value(v_CursorID,3,v_logindate);
            dbms_sql.column_value(v_CursorID,4,v_logoutdate);
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_mid||', '||v_hid||', '||v_logindate||', '||v_logoutdate||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/
set SERVEROUTPUT ON;
declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_fid number(10,0);
v_membersmid number(10,0);
v_address varchar2(200);
v_name varchar2(200);
v_format varchar2(200);
v_files blob;
v_NUMRows integer;
v_count integer:=1;
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','export3.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'FILES') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select membersmid from Files order by membersmid asc) loop
       stmt_str := 'select * from Files where membersmid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.membersmid);
       dbms_sql.define_column(v_CursorID, 1, v_fid);
       dbms_sql.define_column(v_CursorID, 2, v_membersmid);
       dbms_sql.define_column(v_CursorID, 3, v_address,200);
       dbms_sql.define_column(v_CursorID, 4, v_name,200);
       dbms_sql.define_column(v_CursorID, 5, v_format,200);
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_fid);
            dbms_sql.column_value(v_CursorID,2,v_membersmid);
            dbms_sql.column_value(v_CursorID,3,v_address);
            dbms_sql.column_value(v_CursorID,4,v_name);
            dbms_sql.column_value(v_CursorID,5,v_format);
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_fid||', '||v_membersmid||', '||v_address||', '||v_name||', '||v_format||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/
set SERVEROUTPUT ON;
declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_mid number(10,0);
v_tid number(10,0);
v_NUMRows integer;
v_count integer:=1;
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','export4.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'MEMBERS_TAGS') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select mid from Members_Tags order by mid asc) loop
       stmt_str := 'select * from Members_Tags where mid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.mid);
       dbms_sql.define_column(v_CursorID, 1, v_mid);
       dbms_sql.define_column(v_CursorID, 2, v_tid);
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_mid);
            dbms_sql.column_value(v_CursorID,2,v_tid);
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_mid||', '||v_tid||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/
set SERVEROUTPUT ON;
declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_pid number(10,0);
v_rights varchar2(255);
v_NUMRows integer;
v_count integer:=1;
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','export5.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'PRIVACY') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select pid from Privacy order by pid asc) loop
       stmt_str := 'select * from Privacy where pid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.pid);
       dbms_sql.define_column(v_CursorID, 1, v_pid);
       dbms_sql.define_column(v_CursorID, 2, v_rights,200);
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_pid);
            dbms_sql.column_value(v_CursorID,2,v_rights);
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_pid||', '||v_rights||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/
set SERVEROUTPUT ON;
declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_pid number(10,0);
v_fid number(10,0);
v_filesmembersmid number(10,0);
v_NUMRows integer;
v_count integer:=1;
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','export6.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'PRIVACY_FILES') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select pid from Privacy_Files order by pid asc) loop
       stmt_str := 'select * from Privacy_Files where pid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.pid);
       dbms_sql.define_column(v_CursorID, 1, v_pid);
       dbms_sql.define_column(v_CursorID, 2, v_fid);
       dbms_sql.define_column(v_CursorID, 2, v_filesmembersmid);
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_pid);
            dbms_sql.column_value(v_CursorID,2,v_fid);
            dbms_sql.column_value(v_CursorID,2,v_filesmembersmid);
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_pid||', '||v_fid||', '||v_filesmembersmid||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/
set SERVEROUTPUT ON;
declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_pid number(10,0);
v_mid number(10,0);
v_NUMRows integer;
v_count integer:=1;
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','export7.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'PRIVACY_MEMBERS') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select pid from Privacy_Members order by pid asc) loop
       stmt_str := 'select * from Privacy_Members where pid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.pid);
       dbms_sql.define_column(v_CursorID, 1, v_pid);
       dbms_sql.define_column(v_CursorID, 2, v_mid);
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_pid);
            dbms_sql.column_value(v_CursorID,2,v_mid);
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_pid||', '||v_mid||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/
set SERVEROUTPUT ON;
declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_tid number(10,0);
v_name varchar2(255);
v_NUMRows integer;
v_count integer:=1;
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','export8.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'TAGS') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select tid from Tags order by tid asc) loop
       stmt_str := 'select * from Tags where tid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.tid);
       dbms_sql.define_column(v_CursorID, 1, v_tid);
       dbms_sql.define_column(v_CursorID, 2, v_name,200);
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_tid);
            dbms_sql.column_value(v_CursorID,2,v_name);
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_tid||', '||v_name||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/
set SERVEROUTPUT ON;
declare
stmt_str varchar2(200);
v_col varchar2(300);
v_table varchar2(300);
v_column_list varchar2(300);
v_CursorID number;
v_tid number(10,0);
v_fid number(10,0);
v_filesmembersmid number(10,0);
v_NUMRows integer;
v_count integer:=1;
out_file utl_file.file_type;

begin
out_file := utl_file.fopen('NUME','exportp.sql','w');
v_CursorID := dbms_sql.open_cursor;
for v_linie in (select table_name from user_tables where table_name in 'TAGS_FILES') loop
   for v_col in (select column_name, data_type from user_tab_cols where table_name=v_linie.table_name) loop
       v_column_list:=v_column_list||', '||v_col.column_name;
       v_column_list := ltrim(v_column_list,', ');
     end loop;  
       for v_student in (select tid from Tags_Files order by tid asc) loop
       stmt_str := 'select * from Tags_Files where tid = :id_value';
       dbms_sql.parse(v_CursorID, stmt_str, dbms_sql.v7);
       dbms_sql.bind_variable(v_CursorID, 'id_value', v_student.tid);
       dbms_sql.define_column(v_CursorID, 1, v_tid);
       dbms_sql.define_column(v_CursorID, 2, v_fid);
       dbms_sql.define_column(v_CursorID, 2, v_filesmembersmid);
       v_NumRows := dbms_sql.execute(v_CursorID);
       
        loop 
        if dbms_sql.fetch_rows(v_CursorID)=0 then
            exit;
        end if;
    
            dbms_sql.column_value(v_CursorID,1,v_tid);
            dbms_sql.column_value(v_CursorID,2,v_fid);
            dbms_sql.column_value(v_CursorID,2,v_filesmembersmid);
            utl_file.put_line(out_file,v_count||' insert into '||v_linie.table_name||' ('||v_column_list||') values ('||v_tid||', '||v_fid||', '||v_filesmembersmid||')');
            v_count:=v_count+1;
       end loop;
    end loop;
    v_column_list := ltrim(v_column_list,', ');
    dbms_output.put_line(v_column_list);

end loop;
  dbms_sql.close_cursor(v_CursorID);
  utl_file.fclose(out_file);
end;
/