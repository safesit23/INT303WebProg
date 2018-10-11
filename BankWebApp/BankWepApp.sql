CREATE TABLE account (
  accountid int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name varchar(50)  NOT NULL,
  balance int  NOT NULL,
  pin int  NOT NULL,
  PRIMARY KEY (accountid)
);

INSERT INTO account ("NAME", BALANCE, PIN)  VALUES ('Steve',6000,1234);

CREATE TABLE history (
  historyid int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  accountid int NOT NULL,
  method varchar(10) NOT NULL,
  amount int NOT NULL,
  createdate date not null,
  balance int NOT NULL,
  PRIMARY KEY (historyid),
  CONSTRAINT accountid_history FOREIGN KEY (accountid) REFERENCES account (accountid) ON DELETE NO ACTION ON UPDATE NO ACTION
  INSERT INTO history (accountid, method, amount, createdate, balance)  VALUES (1,'deposit',6000,'2018-09-12',6000)
) ;

/* Ref from todo_script */