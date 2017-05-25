set serveroutput on;
DECLARE 
   type namesarray IS VARRAY(600) OF VARCHAR2(255); 
   contor NUMBER(10);
   surrname namesarray; 
   firstname namesarray;
   tagnames namesarray;
   filename namesarray;
   addressname namesarray;
   rand number;
   tip number;
BEGIN 
   firstname := namesarray('Adelle','Adena','Adina','Adolfo','Adolph','Adria','Barry','Bart','Barton','Basil','Basilia','Cierra','Cinda','Cinderella','Cindi','Cindie','Dirk','Divina','Dixie','Dodie','Dollie','Ellena','Elli',
   'Ellie','Elliot','Elliott','Fermin','Fermina','Fern','Fernanda','Fernande','Giuseppe','Giuseppina','Gladis','Glady','Gladys','Hazel','Heath','Heather','Hector','Hedwig','Ignacio','Ike','Ila','Ilana','Ilda','Jacqui','Jacquie',
   'Jacquiline','Jacquline','Jacqulyn','Jada','Jade','Jadwiga','Jae','Jaes','Jaime','Karyn','Kasandra','Kaseyo','Kasey','Kasha','Kasi','Kasie','Kassandra','Kassie','Kate','Katelin','Katelyn','Katelynn','Katerine','Kathaleen',
   'Katharina','Katharine','Katharyn','Kathe','Katheleen','Katherin','Katherina','Katherine','Kathern','Katheryn','Kathey','Kathi','Kathie','Kathleen','Kathlene','Kathline','Kathlyn','Kathrin','Kathrine','Kathryn','Kathryne','Kathy',
   'Kathyrn','Kati','Katia','Katie','Katina','Katlyn','Katrice','Katrina','Kattie','Katy','Kay','Kayce','Kaycee','Kaye','Kayla','Kaylee','Kayleen','Kayleigh','Kaylene','Kazuko','Kecia','Keeley','Keely','Keena','Keenan','Keesha','Keiko',
   'Keila','Keira','Keisha','Keithg','Keith','Keitha','Keli','Kelle','Kellee','Kelleyk','Kelley','Kelli','Kellie','Kellyg','Kelly','Kellye','Kelsey','Kelsi','Kelsie','Kelvin','Kemberly','Ken','Kena','Kenda','Kendal','Kendalla',
   'Kendall','Kendra','Kendrick','Keneth','Kenia','Kenisha','Kenna','Kenneth','Kennetha','Kennith','Leo','Leola','Leoma','Leon','Leons','Leona','Leonard','Leonarda','Leonardo','Leone','Leonel','Leonia','Leonida','Leonie','Leonila',
   'Leonor','Leonora','Leonore','Leontine','Leopoldo','Leora','Leota','Lera','Leroy','Les','Lesa','Lesha','Lesia','Leslee','Lesley','Lesleys','Lesli','Leslie','Leslies','Lessie','Lesters','Lester','Leta','Letha','Leticia',
   'Letisha','Letitia','Lettie','Letty','Levi','Lewi','Lewis','Lexie','Lezlie','Li','Lia','Liana','Liane','Lianne','Libbie','Libby','Liberty','Librada','Lida','Lidia','Lien','Lieselotte','Ligia','Lila','Lili','Lilia',
   'Lilian','Liliana','Lilla','Lilli','Lillia','Lilliam','Lillian','Lilliana','Lillie','Lilly','Lily','Lin','Lina','Lincoln','Linda','Lindsays','Lindsay','Lindsey','Lindseys','Lindsy','Lindy','Linettes','Ling','Linh',
   'Linn','Linnea','Linnie','Lino','Linsey','Linwood','Lionel','Lisa','Lisabeth','Lisandra','Lisbeth','Lise','Lisette','Lisha','Lissa','Lissette','Malia','Malik','Malika','Malinda','Malisa','Malissa','Malka','Mallie',
   'Mallory','Malorie','Malvina','Mamie','Mammie','Mano','Man','Mana','Manda','Mandi','Mandie','Mandy','Manie','Manual','Manuel','Manuela','Many','Mao','Maple','Mara','Maragaret','Maragret','Maranda','Marc','Marcel',
   'Marcela','Marcelene','Marcelina','Marceline','Marcelino','Marcell','Marcella','Marcelle','Marcellus','Marcelo','Marcene','Marchelle','Marci','Marcia','Marcie','Marco','Marcos','Marcus','Marcy','Mardell','Maren','Marg',
   'Margaret','Margareta','Margarete','Margarett','Margaretta','Margarette','Margarita','Margarite','Margarito','Margart','Marge','Margene','Margeret','Margert','Margery','Marget','Margherita','Margie','Margit','Margo',
   'Margorie','Margot','Margret','Margrett','Marguerita','Marguerite','Margurite','Margy','Marhta','Mari','Maria','Mary','Mariah','Mariam','Marian','Mariana','Marianela','Mariann','Marianna','Marianne','Mariano','Maribel',
   'Nedra','Neely','Neida','Neil','Nelda','Nelia','Nelida','Nell','Oda','Odelia','Odell','Odelli','Odessa','Odette','Odilia','Odis','Ofelia','Ok','Ola','Olen','Olene','Oleta','Paulette','Paulina','Pauline',
   'Paulita','Paz','Pearl','Pearle','Pearlene','Pearlie','Pearline','Pearly','Rana','Ranae','Randa','Randal','Randall','Randee','Randell','Randi','Randolph','Randyf','Randy','Ranee','Raphael','Raquel','Rashad',
   'Rasheeda','Rashida','Raul','Raven','Ray','Rays','Raye','Rayford','Raylene','Raymon','Raymond','Raymondi','Raymonde','Raymundo','Rayna','Rea','Reagan','Reanna','Reatha','Reba','Rebbeca','Rebbecca','Rebeca',
   'Rebecca','Rebecka','Rebekah','Reda','Reed','Reena','Refugia','Refugio','Refugiat','Regan','Regena','Regenia','Reggie','Regina','Reginald','Regine','Reginia','Reid','Reiko','Reina','Reinaldo','Reita','Rema',
   'Remedios','Remona','Rena','Renae','Renaldo','Renata','Renate','Renato','Renay','Renda','Reneb','Rene','Renea','Renee','Renetta','Renita','Renna','Ressie','Reta','Retha','Retta','Reuben','Reva',
   'Rex','Rey','Reyes','Reyna','Reynalda','Reynaldo','Rhea','Rheba','Rhett','Rhiannon','Rhoda','Rhona','Rhonda','Ria','Ricarda','Ricardo','Rich','Richard','Richardo','Richelle','Richie','Rick','Rickey','Verlie','Verline','Vern'); 
   surrname := namesarray('Abilez','Abina','Abington','Abitong','Abke','Abkemeier','Ablang','Ablao','Able','Ableman','Abler','Ables','Ablin','Abling','Abner','Abnet','Abney','Abo','Abolafia','Abolt','Abood'
); 
  contor :=0;
  --Members
   FOR j in 1 .. 500 LOOP 
      FOR i in 1.. 20 LOOP
      insert into Members (mid,surname,firstname,username,email,address,phone,password,fictiv,bithdate,deceaseddate)
      VALUES( contor,surrname(i),firstname(j),surrname(i)||firstname(j),surrname(i)||firstname(j)||'@yahoo.com',null ,null ,surrname(i)||firstname(j),0,sysdate-((18*365)+TRUNC(DBMS_RANDOM.VALUE(1,10))*365),null);
      contor := contor+1;
      END LOOP;
   END LOOP;
   --History
    contor :=0;
   FOR j in 1 .. 500 LOOP 
      FOR i in 1.. 20 LOOP
      rand := (TRUNC(DBMS_RANDOM.VALUE(1,10)));
      insert into History (hid,logindate,logoutdate,mid)
      VALUES( contor,sysdate-rand-(TRUNC(DBMS_RANDOM.VALUE(1,10))),sysdate-rand,contor);
      contor := contor+1;
      END LOOP;
   END LOOP; 
   --GenTree
   FOR j in 1 .. 20 LOOP 
      FOR i in 1.. 20 LOOP
      --rand := (TRUNC(DBMS_RANDOM.VALUE(1,10)));
      if( i~= j)then
      insert into GenTree (mid,mid2,relationship)
      VALUES( i ,j, trunc(dbms_random.value(1,4)));
      end if;
      --contor := contor+1;
      END LOOP;
   END LOOP; 
   --Tags
   tagnames:=namesarray('borcan','cutu','zacusca','Garcea','yolo','eula');
   for i in 1 .. 6 loop
    insert into Tags(tid,name) values(i,tagnames(i));
   end loop;
   --Members_Tags
   for i in 1.. 20 loop
      for j in 1..6 loop
      insert into Members_Tags(mid,tid) values (i,j);
      end loop;
   end loop;
   --Privacy
   for i in 1..100 loop
   tip:=trunc(dbms_random.value(1,3));
   if(tip=1)then
      insert into Privacy(pid,rights) values(i,'family');
      end if;
      if(tip=2)then
      insert into Privacy(pid,rights) values(i,'all');
      end if;
      if(tip=3)then
      insert into Privacy(pid,rights) values(i,'nobody');
      end if;
   end loop;
   --Files
   filename:=namesarray('img.jpg','img1.jpg','img2.jpg','img3.jpg','img4.jpg');
   addressname:=namesarray('c://Desktop/img.jpg','c://Desktop/img1.jpg','c://Desktop/img2.jpg','c://Desktop/img3.jpg','c://Desktop/img4.jpg');
   for i in 1 ..5 loop
    insert into Files(fid,membersmid,address,name,format,files) values (i,i,addressname(i),filename(i),'.jpg','54455354');
   end loop;
   --Privacy_Files
   for i in 1..5 loop
    for j in 1..100 loop
      insert into Privacy_Files(pid,fid,filesmembersmid) values (j,i,i);
    end loop;
   end loop;
   --Privacy_Members
   for i in 1..5 loop
    for j in 1..100 loop
      insert into Privacy_Members(pid,mid) values (j,i);
    end loop;
   end loop;
   --Tags_files
   for i in 1..5 loop
    for j in 1..6 loop
      insert into Tags_Files(tid,fid,filesmembersmid) values (j,i,i);
    end loop;
   end loop;
   END;
