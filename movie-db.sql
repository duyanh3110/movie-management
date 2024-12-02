--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2024-12-02 17:24:09 EET

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

DROP DATABASE "movie-management";
--
-- TOC entry 3652 (class 1262 OID 17495)
-- Name: movie-management; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE "movie-management" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';


\connect -reuse-previous=on "dbname='movie-management'"

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

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 3653 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 853 (class 1247 OID 17964)
-- Name: gender; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.gender AS ENUM (
    'FEMALE',
    'MALE',
    'OTHER'
);


--
-- TOC entry 856 (class 1247 OID 17974)
-- Name: userstatus; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.userstatus AS ENUM (
    'ACTIVE',
    'INACTIVE',
    'NONE'
);


--
-- TOC entry 859 (class 1247 OID 17984)
-- Name: usertype; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.usertype AS ENUM (
    'ADMIN',
    'OWNER',
    'USER'
);


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 17807)
-- Name: tbl_group; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tbl_group (
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    description character varying(255),
    id character varying(255) NOT NULL,
    name character varying(255),
    role_id character varying(255),
    created_by character varying(255),
    updated_by character varying(255)
);


--
-- TOC entry 210 (class 1259 OID 17816)
-- Name: tbl_group_has_user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tbl_group_has_user (
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    group_id character varying(255),
    id character varying(255) NOT NULL,
    user_id character varying(255),
    created_by character varying(255),
    updated_by character varying(255)
);


--
-- TOC entry 211 (class 1259 OID 17823)
-- Name: tbl_permission; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tbl_permission (
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    description character varying(255),
    id character varying(255) NOT NULL,
    name character varying(255),
    created_by character varying(255),
    updated_by character varying(255)
);


--
-- TOC entry 212 (class 1259 OID 17830)
-- Name: tbl_role; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tbl_role (
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    description character varying(255),
    id character varying(255) NOT NULL,
    name character varying(255),
    created_by character varying(255),
    updated_by character varying(255)
);


--
-- TOC entry 213 (class 1259 OID 17837)
-- Name: tbl_role_has_permission; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tbl_role_has_permission (
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    id character varying(255) NOT NULL,
    permission_id character varying(255),
    role_id character varying(255),
    created_by character varying(255),
    updated_by character varying(255)
);


--
-- TOC entry 214 (class 1259 OID 17844)
-- Name: tbl_token; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tbl_token (
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    access_token character varying(255),
    id character varying(255) NOT NULL,
    refresh_token character varying(255),
    reset_token character varying(255),
    username character varying(255),
    created_by character varying(255),
    updated_by character varying(255)
);


--
-- TOC entry 215 (class 1259 OID 17853)
-- Name: tbl_user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tbl_user (
    date_of_birth date,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    email character varying(255),
    first_name character varying(255),
    id character varying(255) NOT NULL,
    last_name character varying(255),
    password character varying(255),
    phone character varying(255),
    username character varying(255),
    created_by character varying(255),
    updated_by character varying(255),
    gender public.gender,
    status public.userstatus,
    type public.usertype
);


--
-- TOC entry 216 (class 1259 OID 17860)
-- Name: tbl_user_has_role; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tbl_user_has_role (
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    id character varying(255) NOT NULL,
    role_id character varying(255),
    user_id character varying(255),
    created_by character varying(255),
    updated_by character varying(255)
);


--
-- TOC entry 3639 (class 0 OID 17807)
-- Dependencies: 209
-- Data for Name: tbl_group; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tbl_group VALUES ('2024-12-02 13:22:28', '2024-12-02 13:22:29', 'Manager team of CGV Vincom Ba Trieu', '4ca6fb8e-0221-43e1-8274-431fbf491b31', 'Manager CGV Vincom Ba Trieu', 'b935b2f2-741a-49ed-a4cd-5c89ee6aa785', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_group VALUES ('2024-12-02 13:22:28', '2024-12-02 13:22:29', 'Employee team of CGV Vincom Ba Trieu', '620a9743-8818-4bf9-9a83-bbb22de5fc87', 'Employee CGV Vincom Ba Trieu', '1f6e90ea-368c-4c09-ae80-ba2be2ea1f49', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');


--
-- TOC entry 3640 (class 0 OID 17816)
-- Dependencies: 210
-- Data for Name: tbl_group_has_user; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tbl_group_has_user VALUES ('2024-12-02 13:38:36', '2024-12-02 13:38:37', '4ca6fb8e-0221-43e1-8274-431fbf491b31', 'd60fb694-238e-46a7-90d0-8299f0f7da02', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_group_has_user VALUES ('2024-12-02 13:38:36', '2024-12-02 13:38:37', '620a9743-8818-4bf9-9a83-bbb22de5fc87', 'b4cbb581-69ba-4681-b646-2f70bf7b0bb9', '0f29eef0-d92a-4e63-8f8c-a9990c877f83', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_group_has_user VALUES ('2024-12-02 13:38:36', '2024-12-02 13:38:37', '4ca6fb8e-0221-43e1-8274-431fbf491b31', '4d993526-1900-4e9d-89aa-5bae4aa628fe', '6a3888f7-a9d7-47ef-b1c8-b11beadc0c31', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');


--
-- TOC entry 3641 (class 0 OID 17823)
-- Dependencies: 211
-- Data for Name: tbl_permission; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tbl_permission VALUES ('2024-11-27 17:43:44', '2024-11-27 17:43:47', 'Provides user with access to the entire system and all of its features, with no restrictions', '1d247984-2652-4019-8571-3cee9dbbfdd2', 'Full Access', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_permission VALUES ('2024-11-27 17:47:10', '2024-11-27 17:47:12', 'Provide user with access to be read', '1d247984-2652-4019-8571-3cee9dbbfdd3', 'View', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_permission VALUES ('2024-11-27 17:47:10', '2024-11-27 17:47:12', 'Provide user with access to be create', '1d247984-2652-4019-8571-3cee9dbbfdd4', 'Add', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_permission VALUES ('2024-11-27 17:47:10', '2024-11-27 17:47:12', 'Provide user with access to be update', '1d247984-2652-4019-8571-3cee9dbbfdd5', 'Update', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_permission VALUES ('2024-11-27 17:47:10', '2024-11-27 17:47:12', 'Provide user with access to be delete', '1d247984-2652-4019-8571-3cee9dbbfdd6', 'Delete', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');


--
-- TOC entry 3642 (class 0 OID 17830)
-- Dependencies: 212
-- Data for Name: tbl_role; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tbl_role VALUES ('2024-11-27 18:00:35', '2024-11-27 18:00:36', 'The person who is responsible for managing an organization', 'b935b2f2-741a-49ed-a4cd-5c89ee6aa785', 'Manager', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_role VALUES ('2024-11-27 18:00:35', '2024-11-27 18:00:36', 'The person who is paid to work for someone else', '1f6e90ea-368c-4c09-ae80-ba2be2ea1f49', 'Employee', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_role VALUES ('2024-11-27 18:00:35', '2024-11-27 18:00:36', 'The person who uses a product ', '9423420e-41e4-43b7-8296-f8de058feded', 'User', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');


--
-- TOC entry 3643 (class 0 OID 17837)
-- Dependencies: 213
-- Data for Name: tbl_role_has_permission; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tbl_role_has_permission VALUES ('2024-11-27 18:05:20', '2024-11-27 18:05:22', 'b36700dd-292e-4737-89f1-92c4c56724cb', '1d247984-2652-4019-8571-3cee9dbbfdd2', 'b935b2f2-741a-49ed-a4cd-5c89ee6aa785', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_role_has_permission VALUES ('2024-12-02 13:17:31', '2024-12-02 13:17:33', 'b38558c0-65c6-4234-b363-de395d954494', '1d247984-2652-4019-8571-3cee9dbbfdd3', '1f6e90ea-368c-4c09-ae80-ba2be2ea1f49', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_role_has_permission VALUES ('2024-12-02 13:17:31', '2024-12-02 13:17:33', '7d20bf09-57c6-4120-be65-c65fff18261b', '1d247984-2652-4019-8571-3cee9dbbfdd4', '1f6e90ea-368c-4c09-ae80-ba2be2ea1f49', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_role_has_permission VALUES ('2024-12-02 13:17:31', '2024-12-02 13:17:33', 'cfad7a26-6322-470d-af95-b8576e949968', '1d247984-2652-4019-8571-3cee9dbbfdd5', '1f6e90ea-368c-4c09-ae80-ba2be2ea1f49', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');
INSERT INTO public.tbl_role_has_permission VALUES ('2024-12-02 13:17:31', '2024-12-02 13:17:33', '76a76d3e-7b0d-48eb-926d-eaed6f64211e', '1d247984-2652-4019-8571-3cee9dbbfdd3', '9423420e-41e4-43b7-8296-f8de058feded', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');


--
-- TOC entry 3644 (class 0 OID 17844)
-- Dependencies: 214
-- Data for Name: tbl_token; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3645 (class 0 OID 17853)
-- Dependencies: 215
-- Data for Name: tbl_user; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tbl_user VALUES ('1996-10-31', '2024-11-26 15:57:12.881', '2024-11-26 15:57:12.881', 'mervin.hicks@gmail.com', 'Mervin', '1d247984-2652-4019-8571-3cee9dbbfdd1', 'Hicks', 'password', '090-234-4567', 'mervin.hicks', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1', 'MALE', 'ACTIVE', 'OWNER');
INSERT INTO public.tbl_user VALUES ('1998-01-31', '2024-11-26 15:57:12.881', '2024-11-26 15:57:12.881', 'marilyn.crawford@gmail.com', 'Marilyn', '0f29eef0-d92a-4e63-8f8c-a9990c877f83', 'Crawford', 'password', '090-234-4568', 'marilyn.crawford', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1', 'FEMALE', 'ACTIVE', 'ADMIN');
INSERT INTO public.tbl_user VALUES ('1992-06-20', '2024-11-26 15:57:12.881', '2024-11-26 15:57:12.881', 'lauri.keith@gmail.com', 'Lauri', '898a46ac-c96d-4260-ae6b-a6a3c1b53f2f', 'Keith', 'password', '090-234-4569', 'lauri.keith', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1', 'FEMALE', 'ACTIVE', 'USER');
INSERT INTO public.tbl_user VALUES ('1990-08-02', '2024-12-02 13:46:24', '2024-12-02 13:46:26', 'nestor.peterson@gmail.com', 'Nestor', '6a3888f7-a9d7-47ef-b1c8-b11beadc0c31', 'Petersen', 'password', '090-234-4570', 'nestor.petersen', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1', 'MALE', 'ACTIVE', 'ADMIN');


--
-- TOC entry 3646 (class 0 OID 17860)
-- Dependencies: 216
-- Data for Name: tbl_user_has_role; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.tbl_user_has_role VALUES ('2024-12-02 13:37:53', '2024-12-02 13:37:55', '572b58cd-6b4c-4622-9381-736a3f02adcb', '9423420e-41e4-43b7-8296-f8de058feded', '898a46ac-c96d-4260-ae6b-a6a3c1b53f2f', '1d247984-2652-4019-8571-3cee9dbbfdd1', '1d247984-2652-4019-8571-3cee9dbbfdd1');


--
-- TOC entry 3478 (class 2606 OID 17822)
-- Name: tbl_group_has_user tbl_group_has_user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_group_has_user
    ADD CONSTRAINT tbl_group_has_user_pkey PRIMARY KEY (id);


--
-- TOC entry 3474 (class 2606 OID 17813)
-- Name: tbl_group tbl_group_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_group
    ADD CONSTRAINT tbl_group_pkey PRIMARY KEY (id);


--
-- TOC entry 3476 (class 2606 OID 17815)
-- Name: tbl_group tbl_group_role_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_group
    ADD CONSTRAINT tbl_group_role_id_key UNIQUE (role_id);


--
-- TOC entry 3480 (class 2606 OID 17829)
-- Name: tbl_permission tbl_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_permission
    ADD CONSTRAINT tbl_permission_pkey PRIMARY KEY (id);


--
-- TOC entry 3484 (class 2606 OID 17843)
-- Name: tbl_role_has_permission tbl_role_has_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_role_has_permission
    ADD CONSTRAINT tbl_role_has_permission_pkey PRIMARY KEY (id);


--
-- TOC entry 3482 (class 2606 OID 17836)
-- Name: tbl_role tbl_role_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_role
    ADD CONSTRAINT tbl_role_pkey PRIMARY KEY (id);


--
-- TOC entry 3486 (class 2606 OID 17850)
-- Name: tbl_token tbl_token_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_token
    ADD CONSTRAINT tbl_token_pkey PRIMARY KEY (id);


--
-- TOC entry 3488 (class 2606 OID 17852)
-- Name: tbl_token tbl_token_username_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_token
    ADD CONSTRAINT tbl_token_username_key UNIQUE (username);


--
-- TOC entry 3492 (class 2606 OID 17866)
-- Name: tbl_user_has_role tbl_user_has_role_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_user_has_role
    ADD CONSTRAINT tbl_user_has_role_pkey PRIMARY KEY (id);


--
-- TOC entry 3490 (class 2606 OID 17859)
-- Name: tbl_user tbl_user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_user
    ADD CONSTRAINT tbl_user_pkey PRIMARY KEY (id);


--
-- TOC entry 3497 (class 2606 OID 17887)
-- Name: tbl_role_has_permission fk7qnieb63nch1w822p22wu7il9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_role_has_permission
    ADD CONSTRAINT fk7qnieb63nch1w822p22wu7il9 FOREIGN KEY (role_id) REFERENCES public.tbl_role(id);


--
-- TOC entry 3493 (class 2606 OID 17867)
-- Name: tbl_group fke17sm3r6jxwtd4dsbummwt6y0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_group
    ADD CONSTRAINT fke17sm3r6jxwtd4dsbummwt6y0 FOREIGN KEY (role_id) REFERENCES public.tbl_role(id);


--
-- TOC entry 3496 (class 2606 OID 17882)
-- Name: tbl_role_has_permission fkimpu2q9vas7utdl3gti0w2ied; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_role_has_permission
    ADD CONSTRAINT fkimpu2q9vas7utdl3gti0w2ied FOREIGN KEY (permission_id) REFERENCES public.tbl_permission(id);


--
-- TOC entry 3494 (class 2606 OID 17872)
-- Name: tbl_group_has_user fkk23xt6t0rhoc1s185sekeayf9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_group_has_user
    ADD CONSTRAINT fkk23xt6t0rhoc1s185sekeayf9 FOREIGN KEY (group_id) REFERENCES public.tbl_group(id);


--
-- TOC entry 3499 (class 2606 OID 17897)
-- Name: tbl_user_has_role fklndw06guu8xedftiuosbtbr0a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_user_has_role
    ADD CONSTRAINT fklndw06guu8xedftiuosbtbr0a FOREIGN KEY (user_id) REFERENCES public.tbl_user(id);


--
-- TOC entry 3498 (class 2606 OID 17892)
-- Name: tbl_user_has_role fknbcb2rhv9gavk1vtounetntw0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_user_has_role
    ADD CONSTRAINT fknbcb2rhv9gavk1vtounetntw0 FOREIGN KEY (role_id) REFERENCES public.tbl_role(id);


--
-- TOC entry 3495 (class 2606 OID 17877)
-- Name: tbl_group_has_user fkqpbvdln7wyp9dnjvrx0mrrnk1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tbl_group_has_user
    ADD CONSTRAINT fkqpbvdln7wyp9dnjvrx0mrrnk1 FOREIGN KEY (user_id) REFERENCES public.tbl_user(id);


-- Completed on 2024-12-02 17:24:09 EET

--
-- PostgreSQL database dump complete
--

