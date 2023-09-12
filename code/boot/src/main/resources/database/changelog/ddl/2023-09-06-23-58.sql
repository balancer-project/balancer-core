--liquibase formatted sql
--Add one-time and recurring expense models

--changeset juancrrn:1

create table one_time_expense (
  id uuid primary key,
  user_id uuid not null,
  status varchar(64) not null,
  category varchar(64) not null,
  recipient_id uuid not null,
  concept varchar(128) not null,
  comments text not null,
  amount numeric not null,
  amount_type varchar(64) not null,
  payment_method varchar(64) not null,
  hidden_in_plans boolean not null,
  payment_id uuid null,
  created_at timestamp not null,
  updated_at timestamp not null,
  deleted_at timestamp null
);

create table recurring_expense (
  id uuid primary key,
  user_id uuid not null,
  status varchar(64) not null,
  category varchar(64) not null,
  recipient_id uuid not null,
  concept varchar(128) not null,
  comments text not null,
  amount numeric not null,
  amount_type varchar(64) not null,
  payment_method varchar(64) not null,
  frequency_type varchar(64) not null,
  frequency_parameter integer null,
  first_payment_date date not null,
  last_payment_date date null,
  hidden_in_plans boolean not null,
  created_at timestamp not null,
  updated_at timestamp not null,
  deleted_at timestamp null
);

create table expense_recipient (
  id uuid primary key,
  name varchar(128) not null
);

create table expense_payment (
  id uuid primary key,
  expense_id uuid not null,
  status varchar(64) not null,
  amount numeric not null,
  "date" date not null,
  authorization_date date null
);

create table expense_payment_origin_transaction (
  payment_id uuid not null,
  transaction_id varchar(128) not null,

  unique (payment_id, transaction_id)
);
