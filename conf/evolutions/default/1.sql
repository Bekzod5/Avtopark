# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "BUSSY" ("ID" SERIAL NOT NULL PRIMARY KEY,"NAME" VARCHAR(254) DEFAULT '' NOT NULL);
create table "CARLIST" ("ID" SERIAL NOT NULL PRIMARY KEY,"NOMI" VARCHAR(254) DEFAULT '' NOT NULL,"MODELI" VARCHAR(254) DEFAULT '' NOT NULL,"KORXONA_ID" INTEGER NOT NULL);
alter table "CARLIST" add constraint "JADVAL_FK_KORXONA" foreign key("KORXONA_ID") references "BUSSY"("ID") on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table "CARLIST" drop constraint "JADVAL_FK_KORXONA";
drop table "CARLIST";
drop table "BUSSY";

