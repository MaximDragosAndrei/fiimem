BEGIN
  delete from GenTree;
  delete from History;
  delete from MEMBERS_TAGS;
  delete from Tags_Files;
  delete from PRIVACY_FILES;
  delete from PRIVACY_MEMBERS;
  delete from Files;
  delete  from Members;
  delete from TAGS;
  delete from Privacy;
END;