# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "CARLIST" ("ID" SERIAL NOT NULL PRIMARY KEY,"NOMI" VARCHAR(254) DEFAULT '' NOT NULL,"MODELI" VARCHAR(254) DEFAULT '' NOT NULL);

# --- !Downs

drop table "CARLIST";

