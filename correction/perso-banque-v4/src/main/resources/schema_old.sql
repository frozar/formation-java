--
-- PostgreSQL database dump
--

-- Dumped from database version 10.19 (Ubuntu 10.19-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.19 (Ubuntu 10.19-0ubuntu0.18.04.1)

-- Started on 2021-11-19 13:26:27 +04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE ONLY public.carte_bancaire DROP CONSTRAINT fkqt51kotyrwi68q72gyssfx72n;
ALTER TABLE ONLY public.compte_bancaire_operations DROP CONSTRAINT fkg75at5e3wkv95m6mgt28dcunp;
ALTER TABLE ONLY public.compte_bancaire DROP CONSTRAINT fkbwtlktbulne1704o9686j4ss0;
ALTER TABLE ONLY public.carte_bancaire DROP CONSTRAINT fkbvbxghky5h6jcyv3jcn259wyf;
ALTER TABLE ONLY public.carte_bancaire DROP CONSTRAINT fk9fqnbax1ksjfwtuua0kq4jo87;
ALTER TABLE ONLY public.compte_bancaire DROP CONSTRAINT fk66hy7636ji95xhoa4o97rw1ts;
ALTER TABLE ONLY public.compte_bancaire_operations DROP CONSTRAINT fk4d3mcgqbbgsxt143dnvrl61lb;
ALTER TABLE ONLY public.compte_bancaire_operations DROP CONSTRAINT uk_owq8j1022tayfl6o7eng4777s;
ALTER TABLE ONLY public.banque DROP CONSTRAINT uk_3plaldnuw3rekjd003qd1m57p;
ALTER TABLE ONLY public.personne DROP CONSTRAINT personne_pkey;
ALTER TABLE ONLY public.operation DROP CONSTRAINT operation_pkey;
ALTER TABLE ONLY public.compte_bancaire DROP CONSTRAINT compte_bancaire_pkey;
ALTER TABLE ONLY public.carte_bancaire DROP CONSTRAINT carte_bancaire_pkey;
ALTER TABLE ONLY public.banque DROP CONSTRAINT banque_pkey;
DROP TABLE public.personne;
DROP TABLE public.operation;
DROP TABLE public.compte_bancaire_operations;
DROP TABLE public.compte_bancaire;
DROP TABLE public.carte_bancaire;
DROP TABLE public.banque;
DROP EXTENSION plpgsql;
DROP SCHEMA public;
--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 1 (class 3079 OID 13049)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16462)
-- Name: banque; Type: TABLE; Schema: public; Owner: frozar
--

CREATE TABLE public.banque (
    id uuid NOT NULL,
    code_banque character varying(255),
    numero_compte integer NOT NULL
);


ALTER TABLE public.banque OWNER TO frozar;

--
-- TOC entry 197 (class 1259 OID 16467)
-- Name: carte_bancaire; Type: TABLE; Schema: public; Owner: frozar
--

CREATE TABLE public.carte_bancaire (
    id uuid NOT NULL,
    code_pin character varying(255),
    date_expiration timestamp without time zone,
    num_carte character varying(255),
    banque_id uuid,
    compte_courant_id uuid,
    titulaire_id uuid
);


ALTER TABLE public.carte_bancaire OWNER TO frozar;

--
-- TOC entry 198 (class 1259 OID 16475)
-- Name: compte_bancaire; Type: TABLE; Schema: public; Owner: frozar
--

CREATE TABLE public.compte_bancaire (
    dtype character varying(31) NOT NULL,
    id uuid NOT NULL,
    cle character varying(255),
    code_banque character varying(255),
    code_guichet character varying(255),
    num_compte character varying(255),
    numero_operation integer NOT NULL,
    solde double precision,
    tx_interet double precision,
    banque_id uuid,
    titulaire_id uuid
);


ALTER TABLE public.compte_bancaire OWNER TO frozar;

--
-- TOC entry 199 (class 1259 OID 16483)
-- Name: compte_bancaire_operations; Type: TABLE; Schema: public; Owner: frozar
--

CREATE TABLE public.compte_bancaire_operations (
    compte_bancaire_id uuid NOT NULL,
    operations_id uuid NOT NULL
);


ALTER TABLE public.compte_bancaire_operations OWNER TO frozar;

--
-- TOC entry 200 (class 1259 OID 16486)
-- Name: operation; Type: TABLE; Schema: public; Owner: frozar
--

CREATE TABLE public.operation (
    id uuid NOT NULL,
    date_operation timestamp without time zone,
    libelle character varying(255),
    montant double precision,
    num_operation integer
);


ALTER TABLE public.operation OWNER TO frozar;

--
-- TOC entry 201 (class 1259 OID 16491)
-- Name: personne; Type: TABLE; Schema: public; Owner: frozar
--

CREATE TABLE public.personne (
    id uuid NOT NULL,
    nom character varying(255),
    prenom character varying(255)
);


ALTER TABLE public.personne OWNER TO frozar;

