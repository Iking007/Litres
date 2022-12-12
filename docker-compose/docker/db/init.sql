--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2022-12-11 22:17:34

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
-- TOC entry 3326 (class 1262 OID 16398)
-- Name: lab; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE lab WITH TEMPLATE = template0 ENCODING = 'UTF8';


ALTER DATABASE lab OWNER TO postgres;

\connect lab

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
-- TOC entry 6 (class 2615 OID 16400)
-- Name: books; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA books;


ALTER SCHEMA books OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16402)
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    book_id integer NOT NULL,
    name character varying(60) NOT NULL,
    writer character varying(100),
    description text NOT NULL,
    image text,
    file text
);


ALTER TABLE public.book OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16401)
-- Name: book_book_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.book_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.book_book_id_seq OWNER TO postgres;

--
-- TOC entry 3327 (class 0 OID 0)
-- Dependencies: 215
-- Name: book_book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.book_book_id_seq OWNED BY public.book.book_id;


--
-- TOC entry 3174 (class 2604 OID 16405)
-- Name: book book_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book ALTER COLUMN book_id SET DEFAULT nextval('public.book_book_id_seq'::regclass);


--
-- TOC entry 3320 (class 0 OID 16402)
-- Dependencies: 216
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.book VALUES (7, 'Книга 13', 'автор серии книг из 13', '13 книга из серии', 'NULL', 'NULL');
INSERT INTO public.book VALUES (3, 'Хорошая книга', 'Отличный писатель книг', 'Аллегорическая повесть-сказка, наиболее известное произведение Антуана де Сент-Экзюпери. Сказка рассказывает о Маленьком принце, который посещает различные планеты в космосе, включая Землю.', 'https://фантазеры.рф/wa-data/public/shop/products/87/14/111487/images/249093/249093.750x0.jpg', 'https://www.booksite.ru/archiv_new/02_2016/02.pdf');


--
-- TOC entry 3328 (class 0 OID 0)
-- Dependencies: 215
-- Name: book_book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.book_book_id_seq', 7, true);


--
-- TOC entry 3176 (class 2606 OID 16409)
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_id);


-- Completed on 2022-12-11 22:17:34

--
-- PostgreSQL database dump complete
--

