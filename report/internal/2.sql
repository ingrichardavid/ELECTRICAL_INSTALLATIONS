--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: maestros; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA maestros;


ALTER SCHEMA maestros OWNER TO postgres;

--
-- Name: SCHEMA maestros; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA maestros IS 'Esquema que contiene tablas con datos estáticos, necesarias para realizar búsqueda de valores.';


--
-- Name: negocio; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA negocio;


ALTER SCHEMA negocio OWNER TO postgres;

--
-- Name: SCHEMA negocio; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA negocio IS 'Este esquema contiene la lógica del negocio';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA negocio;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET search_path = maestros, pg_catalog;

--
-- Name: D_INTENSIDAD; Type: DOMAIN; Schema: maestros; Owner: postgres
--

CREATE DOMAIN "D_INTENSIDAD" AS double precision NOT NULL DEFAULT 0;


ALTER DOMAIN maestros."D_INTENSIDAD" OWNER TO postgres;

--
-- Name: D_STATUS; Type: DOMAIN; Schema: maestros; Owner: postgres
--

CREATE DOMAIN "D_STATUS" AS boolean NOT NULL DEFAULT false;


ALTER DOMAIN maestros."D_STATUS" OWNER TO postgres;

--
-- Name: D_TIPO_ACOMETIDA; Type: DOMAIN; Schema: maestros; Owner: postgres
--

CREATE DOMAIN "D_TIPO_ACOMETIDA" AS character(1) NOT NULL
	CONSTRAINT "CHK_TIPO_ACOMETIDA" CHECK (((VALUE = 'S'::bpchar) OR (VALUE = 'A'::bpchar)));


ALTER DOMAIN maestros."D_TIPO_ACOMETIDA" OWNER TO postgres;

--
-- Name: DOMAIN "D_TIPO_ACOMETIDA"; Type: COMMENT; Schema: maestros; Owner: postgres
--

COMMENT ON DOMAIN "D_TIPO_ACOMETIDA" IS 'S = Subterránea
A = Aérea';


--
-- Name: D_TIPO_RESISTENCIA_REACTANCIA; Type: DOMAIN; Schema: maestros; Owner: postgres
--

CREATE DOMAIN "D_TIPO_RESISTENCIA_REACTANCIA" AS character(1) NOT NULL
	CONSTRAINT "CHK_RESISTENCIA_O_REACTANCIA" CHECK (((VALUE = 'R'::bpchar) OR (VALUE = 'T'::bpchar)));


ALTER DOMAIN maestros."D_TIPO_RESISTENCIA_REACTANCIA" OWNER TO postgres;

--
-- Name: D_VALOR; Type: DOMAIN; Schema: maestros; Owner: postgres
--

CREATE DOMAIN "D_VALOR" AS numeric(7,5)
	CONSTRAINT "CHK_MAYOR_O_IGUAL_A_CERO" CHECK ((VALUE >= (0)::numeric));


ALTER DOMAIN maestros."D_VALOR" OWNER TO postgres;

--
-- Name: D_VALOR_2; Type: DOMAIN; Schema: maestros; Owner: postgres
--

CREATE DOMAIN "D_VALOR_2" AS numeric(8,5);


ALTER DOMAIN maestros."D_VALOR_2" OWNER TO postgres;

SET search_path = negocio, pg_catalog;

--
-- Name: D_ANGULO; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_ANGULO" AS numeric(12,5) NOT NULL DEFAULT 0;


ALTER DOMAIN negocio."D_ANGULO" OWNER TO postgres;

--
-- Name: D_APELLIDO; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_APELLIDO" AS character varying(50) NOT NULL;


ALTER DOMAIN negocio."D_APELLIDO" OWNER TO postgres;

--
-- Name: D_AREA; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_AREA" AS numeric(12,5) NOT NULL DEFAULT 0.1;


ALTER DOMAIN negocio."D_AREA" OWNER TO postgres;

--
-- Name: D_CANTIDAD; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_CANTIDAD" AS integer NOT NULL;


ALTER DOMAIN negocio."D_CANTIDAD" OWNER TO postgres;

--
-- Name: D_CIRCUITO_RAMAL; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_CIRCUITO_RAMAL" AS integer NOT NULL DEFAULT 0;


ALTER DOMAIN negocio."D_CIRCUITO_RAMAL" OWNER TO postgres;

--
-- Name: D_CLAVE_USUARIO; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_CLAVE_USUARIO" AS bytea NOT NULL;


ALTER DOMAIN negocio."D_CLAVE_USUARIO" OWNER TO postgres;

--
-- Name: D_CODIGO_INTEGER_NOT_NULL; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_CODIGO_INTEGER_NOT_NULL" AS integer NOT NULL
	CONSTRAINT "CHK_CODIGO_MAYOR_A_CERO" CHECK ((VALUE > 0));


ALTER DOMAIN negocio."D_CODIGO_INTEGER_NOT_NULL" OWNER TO postgres;

--
-- Name: D_CODIGO_INTEGER_NULL; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_CODIGO_INTEGER_NULL" AS integer
	CONSTRAINT "CHK_MAYOR_A_CERO" CHECK ((VALUE > 0));


ALTER DOMAIN negocio."D_CODIGO_INTEGER_NULL" OWNER TO postgres;

--
-- Name: D_CONSTANTE; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_CONSTANTE" AS integer
	CONSTRAINT "CHK_MAYOR_A_CERO" CHECK ((VALUE > 0));


ALTER DOMAIN negocio."D_CONSTANTE" OWNER TO postgres;

--
-- Name: D_DIRECCION; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_DIRECCION" AS character varying(200) DEFAULT NULL::character varying;


ALTER DOMAIN negocio."D_DIRECCION" OWNER TO postgres;

--
-- Name: D_DNI; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_DNI" AS integer NOT NULL;


ALTER DOMAIN negocio."D_DNI" OWNER TO postgres;

--
-- Name: D_ENERGIA; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_ENERGIA" AS integer NOT NULL
	CONSTRAINT "CHK_MAYOR_A_CERO" CHECK ((VALUE > 0));


ALTER DOMAIN negocio."D_ENERGIA" OWNER TO postgres;

--
-- Name: D_ESTATUS; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_ESTATUS" AS boolean NOT NULL DEFAULT true;


ALTER DOMAIN negocio."D_ESTATUS" OWNER TO postgres;

--
-- Name: D_FACTOR_POTENCIA; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_FACTOR_POTENCIA" AS real NOT NULL
	CONSTRAINT "CHK_FACTOR_POTENCIA" CHECK (((VALUE >= (0)::double precision) OR (VALUE <= (1)::double precision)));


ALTER DOMAIN negocio."D_FACTOR_POTENCIA" OWNER TO postgres;

--
-- Name: D_FECHA_REGISTRO; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_FECHA_REGISTRO" AS timestamp with time zone NOT NULL DEFAULT now();


ALTER DOMAIN negocio."D_FECHA_REGISTRO" OWNER TO postgres;

--
-- Name: D_NACIONALIDAD; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_NACIONALIDAD" AS character(1) NOT NULL
	CONSTRAINT "CHK_TIPO_NACIONALIDAD" CHECK ((VALUE = ANY (ARRAY['V'::bpchar, 'E'::bpchar])));


ALTER DOMAIN negocio."D_NACIONALIDAD" OWNER TO postgres;

--
-- Name: D_NOMBRE_100; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_NOMBRE_100" AS character varying(100) NOT NULL;


ALTER DOMAIN negocio."D_NOMBRE_100" OWNER TO postgres;

--
-- Name: D_NOMBRE_20; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_NOMBRE_20" AS character varying(20) NOT NULL;


ALTER DOMAIN negocio."D_NOMBRE_20" OWNER TO postgres;

--
-- Name: D_NOMBRE_50; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_NOMBRE_50" AS character varying(50) NOT NULL;


ALTER DOMAIN negocio."D_NOMBRE_50" OWNER TO postgres;

--
-- Name: D_NOMBRE_50_NULL; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_NOMBRE_50_NULL" AS character varying(50);


ALTER DOMAIN negocio."D_NOMBRE_50_NULL" OWNER TO postgres;

--
-- Name: D_NOMBRE_USUARIO; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_NOMBRE_USUARIO" AS character varying(20) NOT NULL
	CONSTRAINT "CHK_LOWER_NOMBRE_USUARIO" CHECK (((VALUE)::text = lower((VALUE)::text)));


ALTER DOMAIN negocio."D_NOMBRE_USUARIO" OWNER TO postgres;

--
-- Name: D_NUMERO_ELEVADORES; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_NUMERO_ELEVADORES" AS integer NOT NULL
	CONSTRAINT "CHK_MAYOR_A_CERO" CHECK ((VALUE > 0));


ALTER DOMAIN negocio."D_NUMERO_ELEVADORES" OWNER TO postgres;

--
-- Name: D_NUMERO_PERSONAS; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_NUMERO_PERSONAS" AS integer NOT NULL
	CONSTRAINT "CHK_INTERVALO" CHECK (((VALUE > 0) AND (VALUE <= 99)));


ALTER DOMAIN negocio."D_NUMERO_PERSONAS" OWNER TO postgres;

--
-- Name: DOMAIN "D_NUMERO_PERSONAS"; Type: COMMENT; Schema: negocio; Owner: postgres
--

COMMENT ON DOMAIN "D_NUMERO_PERSONAS" IS 'Indica el número de personas soportadas por un elevador.';


--
-- Name: D_POTENCIA; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_POTENCIA" AS integer NOT NULL;


ALTER DOMAIN negocio."D_POTENCIA" OWNER TO postgres;

--
-- Name: D_POTENCIA_TOTAL; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_POTENCIA_TOTAL" AS numeric(17,5) NOT NULL DEFAULT 0.00000;


ALTER DOMAIN negocio."D_POTENCIA_TOTAL" OWNER TO postgres;

--
-- Name: D_SUB_TIPO_CARGA; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_SUB_TIPO_CARGA" AS character(1) NOT NULL
	CONSTRAINT "CHK_SUB_TIPO_CARGA" CHECK ((((VALUE = 'P'::bpchar) OR (VALUE = 'C'::bpchar)) OR (VALUE = 'I'::bpchar)));


ALTER DOMAIN negocio."D_SUB_TIPO_CARGA" OWNER TO postgres;

--
-- Name: DOMAIN "D_SUB_TIPO_CARGA"; Type: COMMENT; Schema: negocio; Owner: postgres
--

COMMENT ON DOMAIN "D_SUB_TIPO_CARGA" IS '''P'' = Potencia, ''C''= Cantidad, ''I''= Intensidad
';


--
-- Name: D_TELEFONO; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_TELEFONO" AS character varying(16);


ALTER DOMAIN negocio."D_TELEFONO" OWNER TO postgres;

--
-- Name: D_TIPO_ILUMINARIA_TOMA_CORRIENTE; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_TIPO_ILUMINARIA_TOMA_CORRIENTE" AS character(1) NOT NULL
	CONSTRAINT "CHK_ILUMINARIA_TOMA_CORRIENTE_SUB_ALIMENTADOR" CHECK (((((VALUE = 'I'::bpchar) OR (VALUE = 'T'::bpchar)) OR (VALUE = 'A'::bpchar)) OR (VALUE = 'N'::bpchar)));


ALTER DOMAIN negocio."D_TIPO_ILUMINARIA_TOMA_CORRIENTE" OWNER TO postgres;

--
-- Name: D_VELOCIDAD; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_VELOCIDAD" AS real NOT NULL
	CONSTRAINT "CHK_INTERVALO" CHECK (((VALUE >= (0.1)::double precision) AND (VALUE <= (9.9)::double precision)));


ALTER DOMAIN negocio."D_VELOCIDAD" OWNER TO postgres;

--
-- Name: DOMAIN "D_VELOCIDAD"; Type: COMMENT; Schema: negocio; Owner: postgres
--

COMMENT ON DOMAIN "D_VELOCIDAD" IS 'Indica la velocidad de un elevador.';


--
-- Name: D_VOLTAJE; Type: DOMAIN; Schema: negocio; Owner: postgres
--

CREATE DOMAIN "D_VOLTAJE" AS integer
	CONSTRAINT "CHK_MAYOR_A_CERO" CHECK ((VALUE > 0));


ALTER DOMAIN negocio."D_VOLTAJE" OWNER TO postgres;

SET search_path = maestros, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: CABALLOS_DE_POTENCIAS; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "CABALLOS_DE_POTENCIAS" (
    codigo integer NOT NULL,
    caballo_de_potencia_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    voltaje_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    intensidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."CABALLOS_DE_POTENCIAS" OWNER TO postgres;

--
-- Name: CABALLOS_DE_POTENCIAS_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "CABALLOS_DE_POTENCIAS_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."CABALLOS_DE_POTENCIAS_codigo_seq" OWNER TO postgres;

--
-- Name: CABALLOS_DE_POTENCIAS_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "CABALLOS_DE_POTENCIAS_codigo_seq" OWNED BY "CABALLOS_DE_POTENCIAS".codigo;


--
-- Name: CABALLO_DE_FUERZA_TRIFASICO; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "CABALLO_DE_FUERZA_TRIFASICO" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_20",
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    valor "D_VALOR_2"
);


ALTER TABLE maestros."CABALLO_DE_FUERZA_TRIFASICO" OWNER TO postgres;

--
-- Name: CABALLO_DE_FUERZA_TRIFASICO_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "CABALLO_DE_FUERZA_TRIFASICO_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."CABALLO_DE_FUERZA_TRIFASICO_codigo_seq" OWNER TO postgres;

--
-- Name: CABALLO_DE_FUERZA_TRIFASICO_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "CABALLO_DE_FUERZA_TRIFASICO_codigo_seq" OWNED BY "CABALLO_DE_FUERZA_TRIFASICO".codigo;


--
-- Name: CABALLO_DE_POTENCIA; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "CABALLO_DE_POTENCIA" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_20",
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    valor "D_VALOR"
);


ALTER TABLE maestros."CABALLO_DE_POTENCIA" OWNER TO postgres;

--
-- Name: CABALLO_DE_POTENCIA_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "CABALLO_DE_POTENCIA_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."CABALLO_DE_POTENCIA_codigo_seq" OWNER TO postgres;

--
-- Name: CABALLO_DE_POTENCIA_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "CABALLO_DE_POTENCIA_codigo_seq" OWNED BY "CABALLO_DE_POTENCIA".codigo;


--
-- Name: CABLE; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "CABLE" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_20"
);


ALTER TABLE maestros."CABLE" OWNER TO postgres;

--
-- Name: CABLE_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "CABLE_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."CABLE_codigo_seq" OWNER TO postgres;

--
-- Name: CABLE_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "CABLE_codigo_seq" OWNED BY "CABLE".codigo;


--
-- Name: CALIBRE; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "CALIBRE" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_20",
    area negocio."D_AREA"
);


ALTER TABLE maestros."CALIBRE" OWNER TO postgres;

--
-- Name: CALIBRES; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "CALIBRES" (
    material_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    temperatura_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    intensidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    calibre_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    codigo integer NOT NULL
);


ALTER TABLE maestros."CALIBRES" OWNER TO postgres;

--
-- Name: CALIBRES_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "CALIBRES_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."CALIBRES_codigo_seq" OWNER TO postgres;

--
-- Name: CALIBRES_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "CALIBRES_codigo_seq" OWNED BY "CALIBRES".codigo;


--
-- Name: CALIBRE_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "CALIBRE_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."CALIBRE_codigo_seq" OWNER TO postgres;

--
-- Name: CALIBRE_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "CALIBRE_codigo_seq" OWNED BY "CALIBRE".codigo;


--
-- Name: CARGA; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "CARGA" (
    codigo integer NOT NULL,
    potencia_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    energia_codigo negocio."D_CODIGO_INTEGER_NULL",
    nombre negocio."D_NOMBRE_100",
    caballo_de_fuerza "D_STATUS",
    secadora "D_STATUS",
    cocina_electrica "D_STATUS",
    tipo_carga_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."CARGA" OWNER TO postgres;

--
-- Name: CARGA_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "CARGA_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."CARGA_codigo_seq" OWNER TO postgres;

--
-- Name: CARGA_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "CARGA_codigo_seq" OWNED BY "CARGA".codigo;


--
-- Name: CONDUCTORES_DE_TIERRA; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "CONDUCTORES_DE_TIERRA" (
    codigo integer NOT NULL,
    intensidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    calibre_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    material_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."CONDUCTORES_DE_TIERRA" OWNER TO postgres;

--
-- Name: CONDUCTORES_DE_TIERRA_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "CONDUCTORES_DE_TIERRA_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."CONDUCTORES_DE_TIERRA_codigo_seq" OWNER TO postgres;

--
-- Name: CONDUCTORES_DE_TIERRA_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "CONDUCTORES_DE_TIERRA_codigo_seq" OWNED BY "CONDUCTORES_DE_TIERRA".codigo;


--
-- Name: DUCTO; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "DUCTO" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_20"
);


ALTER TABLE maestros."DUCTO" OWNER TO postgres;

--
-- Name: DUCTO_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "DUCTO_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."DUCTO_codigo_seq" OWNER TO postgres;

--
-- Name: DUCTO_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "DUCTO_codigo_seq" OWNED BY "DUCTO".codigo;


--
-- Name: ELEVADOR; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "ELEVADOR" (
    codigo integer NOT NULL,
    numero_de_personas_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    velocidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    potencia_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."ELEVADOR" OWNER TO postgres;

--
-- Name: ELEVADOR_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "ELEVADOR_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."ELEVADOR_codigo_seq" OWNER TO postgres;

--
-- Name: ELEVADOR_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "ELEVADOR_codigo_seq" OWNED BY "ELEVADOR".codigo;


--
-- Name: ENERGIA; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "ENERGIA" (
    codigo integer NOT NULL,
    cantidad negocio."D_ENERGIA",
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."ENERGIA" OWNER TO postgres;

--
-- Name: ENERGIA_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "ENERGIA_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."ENERGIA_codigo_seq" OWNER TO postgres;

--
-- Name: ENERGIA_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "ENERGIA_codigo_seq" OWNED BY "ENERGIA".codigo;


--
-- Name: FASE; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "FASE" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_20"
);


ALTER TABLE maestros."FASE" OWNER TO postgres;

--
-- Name: FASE_PORCENTAJE_MOTORES; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "FASE_PORCENTAJE_MOTORES" (
    fase_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    porcentaje_motores_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL
);


ALTER TABLE maestros."FASE_PORCENTAJE_MOTORES" OWNER TO postgres;

--
-- Name: FASE_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "FASE_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."FASE_codigo_seq" OWNER TO postgres;

--
-- Name: FASE_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "FASE_codigo_seq" OWNED BY "FASE".codigo;


--
-- Name: INTENSIDAD; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "INTENSIDAD" (
    codigo integer NOT NULL,
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    intensidad "D_INTENSIDAD"
);


ALTER TABLE maestros."INTENSIDAD" OWNER TO postgres;

--
-- Name: INTENSIDAD_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "INTENSIDAD_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."INTENSIDAD_codigo_seq" OWNER TO postgres;

--
-- Name: INTENSIDAD_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "INTENSIDAD_codigo_seq" OWNED BY "INTENSIDAD".codigo;


--
-- Name: INTERRUPTOR; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "INTERRUPTOR" (
    codigo integer NOT NULL,
    capacidad negocio."D_CANTIDAD",
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."INTERRUPTOR" OWNER TO postgres;

--
-- Name: TABLE "INTERRUPTOR"; Type: COMMENT; Schema: maestros; Owner: postgres
--

COMMENT ON TABLE "INTERRUPTOR" IS 'El interruptor es el Breaker que se utilizará como protección para las fases.';


--
-- Name: INTERRUPTOR_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "INTERRUPTOR_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."INTERRUPTOR_codigo_seq" OWNER TO postgres;

--
-- Name: INTERRUPTOR_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "INTERRUPTOR_codigo_seq" OWNED BY "INTERRUPTOR".codigo;


--
-- Name: MARCA_DE_CABLE; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "MARCA_DE_CABLE" (
    temperatura_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    material_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    cable_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    codigo integer NOT NULL
);


ALTER TABLE maestros."MARCA_DE_CABLE" OWNER TO postgres;

--
-- Name: MARCA_DE_CABLE_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "MARCA_DE_CABLE_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."MARCA_DE_CABLE_codigo_seq" OWNER TO postgres;

--
-- Name: MARCA_DE_CABLE_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "MARCA_DE_CABLE_codigo_seq" OWNED BY "MARCA_DE_CABLE".codigo;


--
-- Name: MATERIAL; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "MATERIAL" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_50"
);


ALTER TABLE maestros."MATERIAL" OWNER TO postgres;

--
-- Name: MATERIAL_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "MATERIAL_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."MATERIAL_codigo_seq" OWNER TO postgres;

--
-- Name: MATERIAL_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "MATERIAL_codigo_seq" OWNED BY "MATERIAL".codigo;


--
-- Name: MOTORES_TRIFASICOS_DE_INDUCCION; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "MOTORES_TRIFASICOS_DE_INDUCCION" (
    caballo_de_fuerza_trifasico_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    voltaje_motores_trifasicos_codigo negocio."D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    intensidad "D_INTENSIDAD"
);


ALTER TABLE maestros."MOTORES_TRIFASICOS_DE_INDUCCION" OWNER TO postgres;

--
-- Name: NUMERO_DE_PERSONAS; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "NUMERO_DE_PERSONAS" (
    codigo integer NOT NULL,
    cantidad negocio."D_CANTIDAD"
);


ALTER TABLE maestros."NUMERO_DE_PERSONAS" OWNER TO postgres;

--
-- Name: NUMERO_DE_PERSONAS_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "NUMERO_DE_PERSONAS_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."NUMERO_DE_PERSONAS_codigo_seq" OWNER TO postgres;

--
-- Name: NUMERO_DE_PERSONAS_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "NUMERO_DE_PERSONAS_codigo_seq" OWNED BY "NUMERO_DE_PERSONAS".codigo;


--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "PORCENTAJE_MOTORES_MONOFASICOS" (
    codigo integer NOT NULL,
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    nombre negocio."D_NOMBRE_50",
    porcentaje negocio."D_CANTIDAD"
);


ALTER TABLE maestros."PORCENTAJE_MOTORES_MONOFASICOS" OWNER TO postgres;

--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "PORCENTAJE_MOTORES_MONOFASICOS_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."PORCENTAJE_MOTORES_MONOFASICOS_codigo_seq" OWNER TO postgres;

--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "PORCENTAJE_MOTORES_MONOFASICOS_codigo_seq" OWNED BY "PORCENTAJE_MOTORES_MONOFASICOS".codigo;


--
-- Name: PORCENTAJE_MOTORES_TRIFASICOS; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "PORCENTAJE_MOTORES_TRIFASICOS" (
    codigo integer NOT NULL,
    tipo_de_motor negocio."D_NOMBRE_50",
    nombre negocio."D_NOMBRE_50",
    porcentaje negocio."D_CANTIDAD",
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."PORCENTAJE_MOTORES_TRIFASICOS" OWNER TO postgres;

--
-- Name: PORCENTAJE_MOTORES_TRIFASICOS_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "PORCENTAJE_MOTORES_TRIFASICOS_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."PORCENTAJE_MOTORES_TRIFASICOS_codigo_seq" OWNER TO postgres;

--
-- Name: PORCENTAJE_MOTORES_TRIFASICOS_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "PORCENTAJE_MOTORES_TRIFASICOS_codigo_seq" OWNED BY "PORCENTAJE_MOTORES_TRIFASICOS".codigo;


--
-- Name: POTENCIA; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "POTENCIA" (
    codigo integer NOT NULL,
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    cantidad negocio."D_POTENCIA"
);


ALTER TABLE maestros."POTENCIA" OWNER TO postgres;

--
-- Name: POTENCIA_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "POTENCIA_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."POTENCIA_codigo_seq" OWNER TO postgres;

--
-- Name: POTENCIA_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "POTENCIA_codigo_seq" OWNED BY "POTENCIA".codigo;


--
-- Name: RESISTENCIA_REACTANCIA; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "RESISTENCIA_REACTANCIA" (
    codigo integer NOT NULL,
    calibre_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    ducto_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    valor_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    material_codigo negocio."D_CODIGO_INTEGER_NULL",
    tipo "D_TIPO_RESISTENCIA_REACTANCIA"
);


ALTER TABLE maestros."RESISTENCIA_REACTANCIA" OWNER TO postgres;

--
-- Name: RESISTENCIA_REACTANCIA_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "RESISTENCIA_REACTANCIA_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."RESISTENCIA_REACTANCIA_codigo_seq" OWNER TO postgres;

--
-- Name: RESISTENCIA_REACTANCIA_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "RESISTENCIA_REACTANCIA_codigo_seq" OWNED BY "RESISTENCIA_REACTANCIA".codigo;


--
-- Name: TEMPERATURA; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "TEMPERATURA" (
    codigo integer NOT NULL,
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL",
    cantidad negocio."D_CANTIDAD"
);


ALTER TABLE maestros."TEMPERATURA" OWNER TO postgres;

--
-- Name: TEMPERATURA_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "TEMPERATURA_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."TEMPERATURA_codigo_seq" OWNER TO postgres;

--
-- Name: TEMPERATURA_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "TEMPERATURA_codigo_seq" OWNED BY "TEMPERATURA".codigo;


--
-- Name: TIPO_CARGA; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "TIPO_CARGA" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_100",
    tipo negocio."D_SUB_TIPO_CARGA"
);


ALTER TABLE maestros."TIPO_CARGA" OWNER TO postgres;

--
-- Name: TIPO_CARGA_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "TIPO_CARGA_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."TIPO_CARGA_codigo_seq" OWNER TO postgres;

--
-- Name: TIPO_CARGA_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "TIPO_CARGA_codigo_seq" OWNED BY "TIPO_CARGA".codigo;


--
-- Name: TIPO_DE_INSTALACION; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "TIPO_DE_INSTALACION" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_50"
);


ALTER TABLE maestros."TIPO_DE_INSTALACION" OWNER TO postgres;

--
-- Name: TIPO_DE_INSTALACION_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "TIPO_DE_INSTALACION_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."TIPO_DE_INSTALACION_codigo_seq" OWNER TO postgres;

--
-- Name: TIPO_DE_INSTALACION_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "TIPO_DE_INSTALACION_codigo_seq" OWNED BY "TIPO_DE_INSTALACION".codigo;


--
-- Name: TIPO_FASE; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "TIPO_FASE" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_20"
);


ALTER TABLE maestros."TIPO_FASE" OWNER TO postgres;

--
-- Name: TIPO_FASE_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "TIPO_FASE_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."TIPO_FASE_codigo_seq" OWNER TO postgres;

--
-- Name: TIPO_FASE_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "TIPO_FASE_codigo_seq" OWNED BY "TIPO_FASE".codigo;


--
-- Name: TUBERIAS; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "TUBERIAS" (
    codigo integer NOT NULL,
    tamano negocio."D_NOMBRE_20",
    dos_conductores "D_INTENSIDAD",
    tres_cuatro_conductores "D_INTENSIDAD"
);


ALTER TABLE maestros."TUBERIAS" OWNER TO postgres;

--
-- Name: TUBERIAS_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "TUBERIAS_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."TUBERIAS_codigo_seq" OWNER TO postgres;

--
-- Name: TUBERIAS_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "TUBERIAS_codigo_seq" OWNED BY "TUBERIAS".codigo;


--
-- Name: UNIDAD; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "UNIDAD" (
    codigo integer NOT NULL,
    nombre negocio."D_NOMBRE_20"
);


ALTER TABLE maestros."UNIDAD" OWNER TO postgres;

--
-- Name: UNIDAD_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "UNIDAD_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."UNIDAD_codigo_seq" OWNER TO postgres;

--
-- Name: UNIDAD_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "UNIDAD_codigo_seq" OWNED BY "UNIDAD".codigo;


--
-- Name: VALOR; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "VALOR" (
    codigo integer NOT NULL,
    valor "D_VALOR"
);


ALTER TABLE maestros."VALOR" OWNER TO postgres;

--
-- Name: VALOR_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "VALOR_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."VALOR_codigo_seq" OWNER TO postgres;

--
-- Name: VALOR_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "VALOR_codigo_seq" OWNED BY "VALOR".codigo;


--
-- Name: VELOCIDAD; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "VELOCIDAD" (
    codigo integer NOT NULL,
    velocidad negocio."D_VELOCIDAD",
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."VELOCIDAD" OWNER TO postgres;

--
-- Name: VELOCIDAD_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "VELOCIDAD_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."VELOCIDAD_codigo_seq" OWNER TO postgres;

--
-- Name: VELOCIDAD_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "VELOCIDAD_codigo_seq" OWNED BY "VELOCIDAD".codigo;


--
-- Name: VOLTAJE; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "VOLTAJE" (
    codigo integer NOT NULL,
    voltaje negocio."D_VOLTAJE",
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."VOLTAJE" OWNER TO postgres;

--
-- Name: VOLTAJE_MOTORES_TRIFASICOS; Type: TABLE; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE TABLE "VOLTAJE_MOTORES_TRIFASICOS" (
    codigo integer NOT NULL,
    voltaje negocio."D_VOLTAJE",
    unidad_codigo negocio."D_CODIGO_INTEGER_NOT_NULL"
);


ALTER TABLE maestros."VOLTAJE_MOTORES_TRIFASICOS" OWNER TO postgres;

--
-- Name: VOLTAJE_MOTORES_TRIFASICOS_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "VOLTAJE_MOTORES_TRIFASICOS_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."VOLTAJE_MOTORES_TRIFASICOS_codigo_seq" OWNER TO postgres;

--
-- Name: VOLTAJE_MOTORES_TRIFASICOS_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "VOLTAJE_MOTORES_TRIFASICOS_codigo_seq" OWNED BY "VOLTAJE_MOTORES_TRIFASICOS".codigo;


--
-- Name: VOLTAJE_codigo_seq; Type: SEQUENCE; Schema: maestros; Owner: postgres
--

CREATE SEQUENCE "VOLTAJE_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE maestros."VOLTAJE_codigo_seq" OWNER TO postgres;

--
-- Name: VOLTAJE_codigo_seq; Type: SEQUENCE OWNED BY; Schema: maestros; Owner: postgres
--

ALTER SEQUENCE "VOLTAJE_codigo_seq" OWNED BY "VOLTAJE".codigo;


SET search_path = negocio, pg_catalog;

--
-- Name: ALIMENTADOR_PRINCIPAL; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "ALIMENTADOR_PRINCIPAL" (
    proyecto_codigo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    proyecto_tipo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    tipo_carga_codigo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    cantidad "D_CANTIDAD",
    intensidad maestros."D_INTENSIDAD",
    potencia maestros."D_INTENSIDAD",
    neutro maestros."D_INTENSIDAD"
);


ALTER TABLE negocio."ALIMENTADOR_PRINCIPAL" OWNER TO postgres;

--
-- Name: AREA; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "AREA" (
    codigo integer NOT NULL,
    proyecto_codigo "D_CODIGO_INTEGER_NOT_NULL",
    proyecto_tipo_instalacion "D_CODIGO_INTEGER_NOT_NULL",
    nombre "D_NOMBRE_50",
    potencia_total "D_POTENCIA_TOTAL",
    neutro "D_POTENCIA_TOTAL",
    cantidad "D_CANTIDAD"
);


ALTER TABLE negocio."AREA" OWNER TO postgres;

--
-- Name: AREA_ILUMINARIA_TOMA_CORRIENTES; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "AREA_ILUMINARIA_TOMA_CORRIENTES" (
    codigo integer NOT NULL,
    area_codigo "D_CODIGO_INTEGER_NOT_NULL",
    calibres_codigo "D_CODIGO_INTEGER_NOT_NULL",
    voltaje_codigo "D_CODIGO_INTEGER_NOT_NULL",
    fase_codigo "D_CODIGO_INTEGER_NOT_NULL",
    tipo "D_TIPO_ILUMINARIA_TOMA_CORRIENTE",
    area_cantidad "D_AREA",
    constante "D_CONSTANTE",
    factor_potencia "D_FACTOR_POTENCIA",
    calibre_codigo "D_CODIGO_INTEGER_NOT_NULL",
    interruptor_codigo "D_CODIGO_INTEGER_NOT_NULL",
    acometida maestros."D_TIPO_ACOMETIDA",
    longitud "D_AREA",
    ducto_codigo "D_CODIGO_INTEGER_NOT_NULL",
    angulo "D_ANGULO",
    calibre_a_usar "D_NOMBRE_50",
    circuito_ramal "D_CIRCUITO_RAMAL",
    tuberia "D_NOMBRE_50",
    tuberia_material "D_NOMBRE_20"
);


ALTER TABLE negocio."AREA_ILUMINARIA_TOMA_CORRIENTES" OWNER TO postgres;

--
-- Name: AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA" (
    area_iluminaria_toma_corriente_codigo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    resistencia_reactancia_codigo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    tipo maestros."D_TIPO_RESISTENCIA_REACTANCIA"
);


ALTER TABLE negocio."AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA" OWNER TO postgres;

--
-- Name: AREA_ILUMINARIA_TOMA_CORRIENTES_codigo_seq; Type: SEQUENCE; Schema: negocio; Owner: postgres
--

CREATE SEQUENCE "AREA_ILUMINARIA_TOMA_CORRIENTES_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE negocio."AREA_ILUMINARIA_TOMA_CORRIENTES_codigo_seq" OWNER TO postgres;

--
-- Name: AREA_ILUMINARIA_TOMA_CORRIENTES_codigo_seq; Type: SEQUENCE OWNED BY; Schema: negocio; Owner: postgres
--

ALTER SEQUENCE "AREA_ILUMINARIA_TOMA_CORRIENTES_codigo_seq" OWNED BY "AREA_ILUMINARIA_TOMA_CORRIENTES".codigo;


--
-- Name: AREA_codigo_seq; Type: SEQUENCE; Schema: negocio; Owner: postgres
--

CREATE SEQUENCE "AREA_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE negocio."AREA_codigo_seq" OWNER TO postgres;

--
-- Name: AREA_codigo_seq; Type: SEQUENCE OWNED BY; Schema: negocio; Owner: postgres
--

ALTER SEQUENCE "AREA_codigo_seq" OWNED BY "AREA".codigo;


--
-- Name: CARGAS_EN_AREAS; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "CARGAS_EN_AREAS" (
    codigo_carga "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    codigo_area "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    potencia "D_POTENCIA_TOTAL",
    cantidad "D_CANTIDAD",
    calibre_fase "D_NOMBRE_50",
    calibre_neutro "D_NOMBRE_50",
    calibre_tierra "D_NOMBRE_50",
    fase_codigo "D_CODIGO_INTEGER_NOT_NULL",
    tuberia "D_NOMBRE_50",
    tuberia_material "D_NOMBRE_20",
    cantidad_en_voltaje "D_POTENCIA_TOTAL"
);


ALTER TABLE negocio."CARGAS_EN_AREAS" OWNER TO postgres;

--
-- Name: CIRCUITO_DE_ILUMINACION; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "CIRCUITO_DE_ILUMINACION" (
    codigo integer NOT NULL,
    proyecto_codigo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    proyecto_tipo_instalacion "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    calibre_fase_neutro "D_NOMBRE_50",
    tuberia "D_NOMBRE_50",
    descripcion "D_NOMBRE_100",
    intensidad_total maestros."D_INTENSIDAD"
);


ALTER TABLE negocio."CIRCUITO_DE_ILUMINACION" OWNER TO postgres;

--
-- Name: CIRCUITO_DE_ILUMINACION_codigo_seq; Type: SEQUENCE; Schema: negocio; Owner: postgres
--

CREATE SEQUENCE "CIRCUITO_DE_ILUMINACION_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE negocio."CIRCUITO_DE_ILUMINACION_codigo_seq" OWNER TO postgres;

--
-- Name: CIRCUITO_DE_ILUMINACION_codigo_seq; Type: SEQUENCE OWNED BY; Schema: negocio; Owner: postgres
--

ALTER SEQUENCE "CIRCUITO_DE_ILUMINACION_codigo_seq" OWNED BY "CIRCUITO_DE_ILUMINACION".codigo;


--
-- Name: CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL" (
    proyecto_codigo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    proyecto_tipo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    fase "D_NOMBRE_50_NULL",
    neutro "D_NOMBRE_50_NULL",
    tierra "D_NOMBRE_50_NULL",
    tuberia_fase "D_NOMBRE_50_NULL",
    tuberia_neutro "D_NOMBRE_50_NULL",
    conductor_fase "D_CANTIDAD",
    conductor_neutro "D_CANTIDAD"
);


ALTER TABLE negocio."CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL" OWNER TO postgres;

--
-- Name: ELEVADORES_EN_LA_INSTALACION; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "ELEVADORES_EN_LA_INSTALACION" (
    codigo integer NOT NULL,
    proyecto_codigo "D_CODIGO_INTEGER_NOT_NULL",
    proyecto_tipo_instalacion "D_CODIGO_INTEGER_NOT_NULL",
    elevador_codigo "D_CODIGO_INTEGER_NOT_NULL",
    calibres_codigo "D_CODIGO_INTEGER_NOT_NULL",
    cantidad "D_CANTIDAD"
);


ALTER TABLE negocio."ELEVADORES_EN_LA_INSTALACION" OWNER TO postgres;

--
-- Name: ELEVADORES_EN_LA_INSTALACION_codigo_seq; Type: SEQUENCE; Schema: negocio; Owner: postgres
--

CREATE SEQUENCE "ELEVADORES_EN_LA_INSTALACION_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE negocio."ELEVADORES_EN_LA_INSTALACION_codigo_seq" OWNER TO postgres;

--
-- Name: ELEVADORES_EN_LA_INSTALACION_codigo_seq; Type: SEQUENCE OWNED BY; Schema: negocio; Owner: postgres
--

ALTER SEQUENCE "ELEVADORES_EN_LA_INSTALACION_codigo_seq" OWNED BY "ELEVADORES_EN_LA_INSTALACION".codigo;


--
-- Name: ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS" (
    elevadores_instalacion_codigo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    resistencia_reactancia_codigo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    tipo maestros."D_TIPO_RESISTENCIA_REACTANCIA"
);


ALTER TABLE negocio."ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS" OWNER TO postgres;

--
-- Name: MOTORES_EN_INSTALACION; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "MOTORES_EN_INSTALACION" (
    codigo integer NOT NULL,
    proyecto_codigo "D_CODIGO_INTEGER_NOT_NULL",
    proyecto_tipo_instalacion "D_CODIGO_INTEGER_NOT_NULL",
    descripcion "D_NOMBRE_100",
    intensidad maestros."D_INTENSIDAD",
    cantidad "D_CANTIDAD",
    calibre_fase "D_NOMBRE_50",
    calibre_neutro "D_NOMBRE_50",
    calibre_tierra "D_NOMBRE_50",
    tipo_fase_codigo "D_CODIGO_INTEGER_NOT_NULL",
    caballo_de_fuerza "D_NOMBRE_20",
    breaker maestros."D_INTENSIDAD",
    tuberia "D_NOMBRE_50",
    tuberia_material "D_NOMBRE_20"
);


ALTER TABLE negocio."MOTORES_EN_INSTALACION" OWNER TO postgres;

--
-- Name: MOTORES_EN_INSTALACION_codigo_seq; Type: SEQUENCE; Schema: negocio; Owner: postgres
--

CREATE SEQUENCE "MOTORES_EN_INSTALACION_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE negocio."MOTORES_EN_INSTALACION_codigo_seq" OWNER TO postgres;

--
-- Name: MOTORES_EN_INSTALACION_codigo_seq; Type: SEQUENCE OWNED BY; Schema: negocio; Owner: postgres
--

ALTER SEQUENCE "MOTORES_EN_INSTALACION_codigo_seq" OWNED BY "MOTORES_EN_INSTALACION".codigo;


--
-- Name: PROYECTO; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "PROYECTO" (
    usuario_nacionalidad "D_NACIONALIDAD",
    usuario_cedula "D_DNI",
    nombre "D_NOMBRE_100",
    codigo integer NOT NULL,
    fecha_registro "D_FECHA_REGISTRO",
    potencia_total "D_POTENCIA_TOTAL",
    tipo_de_instalacion_codigo "D_CODIGO_INTEGER_NOT_NULL" NOT NULL,
    intensidad_motores maestros."D_INTENSIDAD",
    fase_motor "D_NOMBRE_50_NULL",
    tierra_motor "D_NOMBRE_50_NULL",
    tuberia_motor "D_NOMBRE_50_NULL"
);


ALTER TABLE negocio."PROYECTO" OWNER TO postgres;

--
-- Name: PROYECTO_codigo_seq; Type: SEQUENCE; Schema: negocio; Owner: postgres
--

CREATE SEQUENCE "PROYECTO_codigo_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE negocio."PROYECTO_codigo_seq" OWNER TO postgres;

--
-- Name: PROYECTO_codigo_seq; Type: SEQUENCE OWNED BY; Schema: negocio; Owner: postgres
--

ALTER SEQUENCE "PROYECTO_codigo_seq" OWNED BY "PROYECTO".codigo;


--
-- Name: USUARIO; Type: TABLE; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE TABLE "USUARIO" (
    nacionalidad "D_NACIONALIDAD" NOT NULL,
    cedula "D_DNI" NOT NULL,
    nombre "D_NOMBRE_50",
    apellido "D_APELLIDO",
    direccion "D_DIRECCION",
    nombre_usuario "D_NOMBRE_USUARIO",
    estatus "D_ESTATUS",
    clave_usuario "D_CLAVE_USUARIO",
    telefono "D_TELEFONO",
    fecha_registro "D_FECHA_REGISTRO"
);


ALTER TABLE negocio."USUARIO" OWNER TO postgres;

SET search_path = maestros, pg_catalog;

--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CABALLOS_DE_POTENCIAS" ALTER COLUMN codigo SET DEFAULT nextval('"CABALLOS_DE_POTENCIAS_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CABALLO_DE_FUERZA_TRIFASICO" ALTER COLUMN codigo SET DEFAULT nextval('"CABALLO_DE_FUERZA_TRIFASICO_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CABALLO_DE_POTENCIA" ALTER COLUMN codigo SET DEFAULT nextval('"CABALLO_DE_POTENCIA_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CABLE" ALTER COLUMN codigo SET DEFAULT nextval('"CABLE_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CALIBRE" ALTER COLUMN codigo SET DEFAULT nextval('"CALIBRE_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CALIBRES" ALTER COLUMN codigo SET DEFAULT nextval('"CALIBRES_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CARGA" ALTER COLUMN codigo SET DEFAULT nextval('"CARGA_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CONDUCTORES_DE_TIERRA" ALTER COLUMN codigo SET DEFAULT nextval('"CONDUCTORES_DE_TIERRA_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "DUCTO" ALTER COLUMN codigo SET DEFAULT nextval('"DUCTO_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "ELEVADOR" ALTER COLUMN codigo SET DEFAULT nextval('"ELEVADOR_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "ENERGIA" ALTER COLUMN codigo SET DEFAULT nextval('"ENERGIA_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "FASE" ALTER COLUMN codigo SET DEFAULT nextval('"FASE_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "INTENSIDAD" ALTER COLUMN codigo SET DEFAULT nextval('"INTENSIDAD_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "INTERRUPTOR" ALTER COLUMN codigo SET DEFAULT nextval('"INTERRUPTOR_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "MARCA_DE_CABLE" ALTER COLUMN codigo SET DEFAULT nextval('"MARCA_DE_CABLE_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "MATERIAL" ALTER COLUMN codigo SET DEFAULT nextval('"MATERIAL_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "NUMERO_DE_PERSONAS" ALTER COLUMN codigo SET DEFAULT nextval('"NUMERO_DE_PERSONAS_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "PORCENTAJE_MOTORES_MONOFASICOS" ALTER COLUMN codigo SET DEFAULT nextval('"PORCENTAJE_MOTORES_MONOFASICOS_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "PORCENTAJE_MOTORES_TRIFASICOS" ALTER COLUMN codigo SET DEFAULT nextval('"PORCENTAJE_MOTORES_TRIFASICOS_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "POTENCIA" ALTER COLUMN codigo SET DEFAULT nextval('"POTENCIA_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "RESISTENCIA_REACTANCIA" ALTER COLUMN codigo SET DEFAULT nextval('"RESISTENCIA_REACTANCIA_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "TEMPERATURA" ALTER COLUMN codigo SET DEFAULT nextval('"TEMPERATURA_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "TIPO_CARGA" ALTER COLUMN codigo SET DEFAULT nextval('"TIPO_CARGA_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "TIPO_DE_INSTALACION" ALTER COLUMN codigo SET DEFAULT nextval('"TIPO_DE_INSTALACION_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "TIPO_FASE" ALTER COLUMN codigo SET DEFAULT nextval('"TIPO_FASE_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "TUBERIAS" ALTER COLUMN codigo SET DEFAULT nextval('"TUBERIAS_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "UNIDAD" ALTER COLUMN codigo SET DEFAULT nextval('"UNIDAD_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "VALOR" ALTER COLUMN codigo SET DEFAULT nextval('"VALOR_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "VELOCIDAD" ALTER COLUMN codigo SET DEFAULT nextval('"VELOCIDAD_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "VOLTAJE" ALTER COLUMN codigo SET DEFAULT nextval('"VOLTAJE_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "VOLTAJE_MOTORES_TRIFASICOS" ALTER COLUMN codigo SET DEFAULT nextval('"VOLTAJE_MOTORES_TRIFASICOS_codigo_seq"'::regclass);


SET search_path = negocio, pg_catalog;

--
-- Name: codigo; Type: DEFAULT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA" ALTER COLUMN codigo SET DEFAULT nextval('"AREA_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES" ALTER COLUMN codigo SET DEFAULT nextval('"AREA_ILUMINARIA_TOMA_CORRIENTES_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "CIRCUITO_DE_ILUMINACION" ALTER COLUMN codigo SET DEFAULT nextval('"CIRCUITO_DE_ILUMINACION_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "ELEVADORES_EN_LA_INSTALACION" ALTER COLUMN codigo SET DEFAULT nextval('"ELEVADORES_EN_LA_INSTALACION_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "MOTORES_EN_INSTALACION" ALTER COLUMN codigo SET DEFAULT nextval('"MOTORES_EN_INSTALACION_codigo_seq"'::regclass);


--
-- Name: codigo; Type: DEFAULT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "PROYECTO" ALTER COLUMN codigo SET DEFAULT nextval('"PROYECTO_codigo_seq"'::regclass);


SET search_path = maestros, pg_catalog;

--
-- Data for Name: CABALLOS_DE_POTENCIAS; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "CABALLOS_DE_POTENCIAS" (codigo, caballo_de_potencia_codigo, voltaje_codigo, intensidad_codigo) FROM stdin;
1	1	2	73
2	1	3	94
3	1	4	95
4	1	6	106
5	2	2	74
6	2	3	93
7	2	4	96
8	2	6	107
9	3	2	75
10	3	3	92
11	3	4	97
12	3	6	108
13	4	2	76
14	4	3	91
15	4	4	98
16	4	6	109
17	5	2	77
18	5	3	90
19	5	4	99
20	5	6	110
21	6	2	78
22	6	3	89
23	6	4	100
24	6	6	111
25	7	2	1
26	7	3	88
27	7	4	101
28	7	6	112
29	8	2	79
30	8	3	87
31	8	4	102
32	8	6	113
33	9	2	80
34	9	3	86
35	9	4	103
36	9	6	114
37	10	2	81
38	10	3	85
39	10	4	104
40	10	6	115
41	11	2	82
42	11	3	84
43	11	4	105
44	11	6	5
45	12	2	14
46	12	3	83
47	12	4	7
48	12	6	6
\.


--
-- Name: CABALLOS_DE_POTENCIAS_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"CABALLOS_DE_POTENCIAS_codigo_seq"', 48, true);


--
-- Data for Name: CABALLO_DE_FUERZA_TRIFASICO; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "CABALLO_DE_FUERZA_TRIFASICO" (codigo, nombre, unidad_codigo, valor) FROM stdin;
1	1/2	8	0.50000
2	3/4	8	0.75000
3	1	8	1.00000
4	1 1/2	8	1.50000
5	2	8	2.00000
6	3	8	3.00000
7	5	8	4.00000
8	7 1/2	8	7.50000
9	10	8	10.00000
10	15	8	15.00000
11	20	8	20.00000
12	25	8	25.00000
13	30	8	30.00000
14	40	8	40.00000
15	50	8	50.00000
16	60	8	60.00000
17	75	8	75.00000
18	100	8	100.00000
19	125	8	125.00000
20	150	8	150.00000
21	200	8	200.00000
22	250	8	250.00000
23	300	8	300.00000
24	350	8	350.00000
25	400	8	400.00000
26	450	8	450.00000
27	500	8	500.00000
\.


--
-- Name: CABALLO_DE_FUERZA_TRIFASICO_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"CABALLO_DE_FUERZA_TRIFASICO_codigo_seq"', 27, true);


--
-- Data for Name: CABALLO_DE_POTENCIA; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "CABALLO_DE_POTENCIA" (codigo, nombre, unidad_codigo, valor) FROM stdin;
1	1/6	8	0.16666
2	1/4	8	0.25000
3	1/3	8	0.33333
4	1/2	8	0.50000
5	3/4	8	0.75000
6	1	8	1.00000
7	1 1/2	8	1.50000
8	2	8	2.00000
9	3	8	3.00000
10	5	8	5.00000
11	7 1/2	8	7.50000
12	10	8	10.00000
\.


--
-- Name: CABALLO_DE_POTENCIA_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"CABALLO_DE_POTENCIA_codigo_seq"', 12, true);


--
-- Data for Name: CABLE; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "CABLE" (codigo, nombre) FROM stdin;
1	+TW
2	+UF
3	+FEPW
4	+RH
5	+RHW
6	+THW
7	+THWN
8	+XHHW
9	+USE
10	+ZW
11	TTU
12	V
13	TA
14	TBS
15	SA
16	AVB
17	SIS
18	+FEP
19	+FEPB
20	+RHH
21	+THHN
\.


--
-- Name: CABLE_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"CABLE_codigo_seq"', 21, true);


--
-- Data for Name: CALIBRE; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "CALIBRE" (codigo, nombre, area) FROM stdin;
1	1/0	53.50000
2	2/0	67.40000
3	3/0	85.00000
4	4/0	107.00000
5	2	33.60000
6	4	21.20000
7	6	13.30000
8	8	8.35000
9	10	5.27000
10	12	3.31000
11	14	2.08000
12	16	0.00000
13	18	0.00000
14	250	126.64400
15	300	152.01200
16	350	177.34800
17	400	202.68300
18	500	253.35400
19	600	304.02500
20	700	354.69500
21	750	380.03700
22	800	0.00000
23	900	0.00000
24	1000	0.00000
25	1250	0.00000
26	1500	0.00000
27	1750	0.00000
28	2000	0.00000
\.


--
-- Data for Name: CALIBRES; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "CALIBRES" (material_codigo, temperatura_codigo, intensidad_codigo, calibre_codigo, codigo) FROM stdin;
1	1	1	11	1
1	1	2	10	2
1	1	3	9	3
1	1	5	8	4
1	1	7	7	5
1	1	9	6	6
1	1	13	5	7
1	1	15	1	8
1	1	20	2	9
1	1	23	3	10
1	1	28	4	11
1	1	32	14	12
1	1	35	15	13
1	1	38	16	14
1	1	40	17	15
1	1	43	18	16
1	2	1	11	17
1	2	2	10	18
1	2	4	9	19
1	2	6	8	20
1	2	8	7	21
1	2	11	6	22
1	2	15	5	23
1	2	21	1	24
1	2	25	2	25
1	2	29	3	26
1	2	34	4	27
1	2	37	14	28
1	2	41	15	29
1	2	42	16	30
1	2	45	17	31
1	2	49	18	32
2	1	1	10	33
2	1	2	9	34
2	1	3	8	35
2	1	5	7	36
2	1	7	6	37
2	1	10	5	38
2	1	14	1	39
2	1	15	2	40
2	1	18	3	41
2	1	21	4	42
2	1	24	14	43
2	1	27	15	44
2	1	31	16	45
2	1	33	17	46
2	1	38	18	47
2	2	1	10	48
2	2	3	9	49
2	2	5	8	50
2	2	6	7	51
2	2	8	6	52
2	2	12	5	53
2	2	16	1	54
2	2	19	2	55
2	2	22	3	56
2	2	26	4	57
2	2	30	14	58
2	2	34	15	59
2	2	36	16	60
2	2	39	17	61
2	2	42	18	62
1	1	47	19	63
1	1	50	20	64
1	1	52	21	65
1	2	55	19	66
1	2	60	20	67
1	2	62	21	68
2	1	41	19	69
2	1	42	20	70
2	1	43	21	71
2	2	46	19	72
2	2	48	20	73
2	2	50	21	74
\.


--
-- Name: CALIBRES_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"CALIBRES_codigo_seq"', 74, true);


--
-- Name: CALIBRE_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"CALIBRE_codigo_seq"', 28, true);


--
-- Data for Name: CARGA; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "CARGA" (codigo, potencia_codigo, energia_codigo, nombre, caballo_de_fuerza, secadora, cocina_electrica, tipo_carga_codigo) FROM stdin;
1	6	1	Aire acondicionado	f	f	f	1
2	8	2	Aire acondicionado	f	f	f	1
3	10	3	Aire acondicionado	f	f	f	1
4	12	4	Aire acondicionado	f	f	f	1
5	13	5	Aire acondicionado	f	f	f	1
6	15	6	Aire acondicionado	f	f	f	1
7	18	7	Aire acondicionado Central (en 208V Trifísico)	f	f	f	1
8	20	8	Aire acondicionado Central	f	f	f	1
9	3	\N	Calentador de agua de 30lts	f	f	f	2
10	5	\N	Calentador de agua de 50lts	f	f	f	2
11	7	\N	Calentador de agua de 80lts	f	f	f	2
12	19	\N	Cocina eléctrica	f	f	t	3
13	14	\N	Cocina de dos unidades	f	f	f	4
14	11	\N	Estufa - típico	f	f	f	5
15	16	\N	Horno convencional	f	f	f	6
18	4	\N	Plancha normal	f	f	f	8
19	7	\N	Plancha al vapor	f	f	f	8
20	1	\N	Pulidora de pisos	f	f	f	9
21	2	\N	Refrigerador grande	f	f	f	10
22	17	\N	Secadora de ropa	f	t	f	11
17	9	\N	Lavaplatos	t	f	f	13
16	8	\N	Trituradora de Alimentos	t	f	f	14
\.


--
-- Name: CARGA_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"CARGA_codigo_seq"', 22, true);


--
-- Data for Name: CONDUCTORES_DE_TIERRA; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "CONDUCTORES_DE_TIERRA" (codigo, intensidad_codigo, calibre_codigo, material_codigo) FROM stdin;
1	116	11	1
2	1	10	1
3	3	9	1
4	5	9	1
5	117	9	1
6	14	8	1
7	29	7	1
8	118	6	1
9	52	5	1
10	119	5	1
11	120	1	1
12	121	1	1
13	122	2	1
14	123	3	1
15	124	4	1
16	125	14	1
17	126	16	1
18	127	17	1
19	128	18	1
20	129	20	1
21	130	22	1
\.


--
-- Name: CONDUCTORES_DE_TIERRA_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"CONDUCTORES_DE_TIERRA_codigo_seq"', 21, true);


--
-- Data for Name: DUCTO; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "DUCTO" (codigo, nombre) FROM stdin;
1	PVC
2	Aluminio
3	Acero
\.


--
-- Name: DUCTO_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"DUCTO_codigo_seq"', 3, true);


--
-- Data for Name: ELEVADOR; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "ELEVADOR" (codigo, numero_de_personas_codigo, velocidad_codigo, potencia_codigo) FROM stdin;
1	1	1	19
2	1	2	20
3	1	3	21
4	1	4	24
5	1	5	24
6	2	1	22
7	2	2	25
8	2	3	27
9	2	4	28
10	2	5	28
11	3	1	23
12	3	2	27
13	3	3	28
14	3	4	28
15	3	5	28
16	4	1	26
17	4	2	28
18	4	3	28
19	4	4	28
20	4	5	29
21	5	1	28
22	5	2	28
23	5	3	29
24	5	4	30
25	5	5	30
26	6	1	28
27	6	2	29
28	6	3	29
29	6	4	30
30	6	5	31
\.


--
-- Name: ELEVADOR_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"ELEVADOR_codigo_seq"', 30, true);


--
-- Data for Name: ENERGIA; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "ENERGIA" (codigo, cantidad, unidad_codigo) FROM stdin;
1	9000	1
2	10000	1
3	12000	1
4	15000	1
5	18000	1
6	24000	1
7	36000	1
8	60000	1
\.


--
-- Name: ENERGIA_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"ENERGIA_codigo_seq"', 8, true);


--
-- Data for Name: FASE; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "FASE" (codigo, nombre) FROM stdin;
1	Monofásico 2 Hilos
2	Monofásico 3 Hilos
3	Trifásico 4 Hilos
\.


--
-- Data for Name: FASE_PORCENTAJE_MOTORES; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "FASE_PORCENTAJE_MOTORES" (fase_codigo, porcentaje_motores_codigo) FROM stdin;
1	1
1	2
1	3
1	4
2	1
2	2
2	3
2	4
\.


--
-- Name: FASE_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"FASE_codigo_seq"', 3, true);


--
-- Data for Name: INTENSIDAD; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "INTENSIDAD" (codigo, unidad_codigo, intensidad) FROM stdin;
1	4	20
2	4	25
3	4	30
4	4	35
5	4	40
6	4	50
7	4	55
8	4	65
9	4	70
10	4	75
11	4	85
12	4	90
13	4	95
14	4	100
15	4	115
16	4	120
17	4	125
18	4	130
19	4	135
20	4	145
21	4	150
22	4	155
23	4	165
24	4	170
25	4	175
26	4	180
27	4	190
28	4	195
29	4	200
30	4	205
31	4	210
32	4	215
33	4	225
34	4	230
35	4	240
36	4	250
37	4	255
38	4	260
39	4	270
40	4	280
41	4	285
42	4	310
43	4	320
44	4	330
45	4	335
46	4	340
47	4	355
48	4	375
49	4	380
50	4	385
51	4	395
52	4	400
53	4	405
54	4	410
55	4	420
56	4	425
57	4	435
58	4	445
59	4	455
60	4	460
61	4	470
62	4	475
63	4	485
64	4	490
65	4	495
66	4	520
67	4	545
68	4	560
69	4	590
70	4	625
71	4	650
72	4	665
73	4	4.4000000000000004
74	4	5.7999999999999998
75	4	7.2000000000000002
76	4	9.8000000000000007
77	4	13.800000000000001
78	4	16
79	4	24
80	4	34
81	4	56
82	4	80
83	4	105
84	4	84
85	4	58.799999999999997
86	4	35.700000000000003
87	4	25.199999999999999
88	4	21
89	4	16.800000000000001
90	4	14.49
91	4	10.289999999999999
92	4	7.5599999999999996
93	4	6.0899999999999999
94	4	4.6200000000000001
95	4	2.3999999999999999
96	4	3.2000000000000002
97	4	4
98	4	5.4000000000000004
99	4	7.5999999999999996
100	4	8.8000000000000007
101	4	11
102	4	13.199999999999999
103	4	18.699999999999999
104	4	30.800000000000001
105	4	44
106	4	28
107	4	17
108	4	12
109	4	10
110	4	8
111	4	6.9000000000000004
112	4	4.9000000000000004
113	4	3.6000000000000001
114	4	2.8999999999999999
115	4	2.2000000000000002
116	4	15
117	4	60
118	4	300
119	4	500
120	4	600
121	4	800
122	4	1000
123	4	1200
124	4	1600
125	4	2000
126	4	2500
127	4	3000
128	4	4000
129	4	5000
130	4	6000
\.


--
-- Name: INTENSIDAD_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"INTENSIDAD_codigo_seq"', 130, true);


--
-- Data for Name: INTERRUPTOR; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "INTERRUPTOR" (codigo, capacidad, unidad_codigo) FROM stdin;
1	15	4
2	20	4
3	25	4
4	30	4
5	35	4
6	40	4
7	45	4
8	50	4
9	60	4
10	70	4
11	80	4
12	90	4
13	100	4
14	110	4
15	125	4
16	150	4
17	175	4
18	200	4
19	225	4
20	300	4
21	350	4
22	400	4
23	450	4
24	500	4
25	600	4
26	700	4
27	800	4
28	1000	4
29	1200	4
30	1600	4
31	2000	4
32	2500	4
33	3000	4
34	4000	4
35	5000	4
36	6000	4
\.


--
-- Name: INTERRUPTOR_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"INTERRUPTOR_codigo_seq"', 36, true);


--
-- Data for Name: MARCA_DE_CABLE; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "MARCA_DE_CABLE" (temperatura_codigo, material_codigo, cable_codigo, codigo) FROM stdin;
1	1	1	1
1	1	2	2
1	2	1	3
1	2	2	4
2	1	3	5
2	1	4	6
2	1	5	7
2	1	6	8
2	1	7	9
2	1	8	10
2	1	9	11
2	1	10	12
2	1	11	13
2	2	4	14
2	2	5	15
2	2	6	16
2	2	8	17
2	2	9	18
2	2	11	19
\.


--
-- Name: MARCA_DE_CABLE_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"MARCA_DE_CABLE_codigo_seq"', 19, true);


--
-- Data for Name: MATERIAL; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "MATERIAL" (codigo, nombre) FROM stdin;
1	Cobre (Cu)
2	Aluminio (Al)
\.


--
-- Name: MATERIAL_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"MATERIAL_codigo_seq"', 2, true);


--
-- Data for Name: MOTORES_TRIFASICOS_DE_INDUCCION; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "MOTORES_TRIFASICOS_DE_INDUCCION" (caballo_de_fuerza_trifasico_codigo, voltaje_motores_trifasicos_codigo, intensidad) FROM stdin;
1	1	4.4000000000000004
1	2	2.5
1	3	2.3999999999999999
1	4	2.2000000000000002
1	5	1.1000000000000001
1	6	0.90000000000000002
2	1	6.4000000000000004
2	2	3.7000000000000002
2	3	3.5
2	4	3.2000000000000002
2	5	1.6000000000000001
2	6	1.3
3	1	8.4000000000000004
3	2	4.7999999999999998
3	3	4.5999999999999996
3	4	4.2000000000000002
3	5	2.1000000000000001
3	6	1.7
4	1	12
4	2	6.9000000000000004
4	3	6.5999999999999996
4	4	6
4	5	3
4	6	2.3999999999999999
5	1	13.6
5	2	7.7999999999999998
5	3	7.5
5	4	6.7999999999999998
5	5	3.3999999999999999
5	6	2.7000000000000002
6	2	11
6	3	10.6
6	4	9.5999999999999996
6	5	4.7999999999999998
6	6	3.8999999999999999
7	2	17.5
7	3	16.699999999999999
7	4	15.199999999999999
7	5	7.5999999999999996
7	6	6.0999999999999996
8	2	25.300000000000001
8	3	24.199999999999999
8	4	22
8	5	11
8	6	9
9	2	32.200000000000003
9	3	30.800000000000001
9	4	28
9	5	14
9	6	11
10	2	48.299999999999997
10	3	46.200000000000003
10	4	42
10	5	21
10	6	17
11	2	62.100000000000001
11	3	59.399999999999999
11	4	54
11	5	27
11	6	22
12	2	78.200000000000003
12	3	74.799999999999997
12	4	68
12	5	34
12	6	27
13	2	92
13	3	88
13	4	80
13	5	40
13	6	32
14	2	120
14	3	114
14	4	104
14	5	52
14	6	41
15	2	150
15	3	143
15	4	130
15	5	65
15	6	52
16	2	177
16	3	169
16	4	154
16	5	77
16	6	62
16	7	16
17	2	221
17	3	211
17	4	192
17	5	96
17	6	77
17	7	20
18	2	285
18	3	273
18	4	248
18	5	124
18	6	99
18	7	26
19	2	359
19	3	343
19	4	312
19	5	156
19	6	125
19	7	31
20	2	414
20	3	396
20	4	360
20	5	180
20	6	144
20	7	37
21	2	552
21	3	528
21	4	480
21	5	240
21	6	192
21	7	49
22	1	302
22	2	242
22	3	60
23	1	361
23	2	289
23	3	72
24	1	414
24	2	336
24	3	83
25	1	477
25	2	382
25	3	95
26	1	515
26	2	412
26	3	103
27	1	590
27	2	472
27	3	118
\.


--
-- Data for Name: NUMERO_DE_PERSONAS; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "NUMERO_DE_PERSONAS" (codigo, cantidad) FROM stdin;
1	4
2	8
3	10
4	13
5	16
6	20
\.


--
-- Name: NUMERO_DE_PERSONAS_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"NUMERO_DE_PERSONAS_codigo_seq"', 6, true);


--
-- Data for Name: PORCENTAJE_MOTORES_MONOFASICOS; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "PORCENTAJE_MOTORES_MONOFASICOS" (codigo, unidad_codigo, nombre, porcentaje) FROM stdin;
1	9	Fusible sin retardo de tiempo	300
2	9	Fusible con retardo de tiempo	175
3	9	Interruptor automático con retardo de tiempo	800
4	9	Interruptor automático de tiempo inverso	250
\.


--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"PORCENTAJE_MOTORES_MONOFASICOS_codigo_seq"', 4, true);


--
-- Data for Name: PORCENTAJE_MOTORES_TRIFASICOS; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "PORCENTAJE_MOTORES_TRIFASICOS" (codigo, tipo_de_motor, nombre, porcentaje, unidad_codigo) FROM stdin;
1	Jaula de Ardilla	Fusible sin retardo de tiempo	300	9
2	Jaula de Ardilla	Fusible con retardo de tiempo	175	9
3	Jaula de Ardilla	Interruptor automático con retardo de tiempo	800	9
4	Jaula de Ardilla	Interruptor automático de tiempo inverso	250	9
5	Los de Tipo E	Fusible sin retardo de tiempo	300	9
6	Los de Tipo E	Fusible con retardo de tiempo	175	9
7	Los de Tipo E	Interruptor automático con retardo de tiempo	1100	9
8	Los de Tipo E	Interruptor automático de tiempo inverso	250	9
9	Soneranos	Fusible sin retardo de tiempo	300	9
10	Soneranos	Fusible con retardo de tiempo	175	9
11	Soneranos	Interruptor automático con retardo de tiempo	800	9
12	Soneranos	Interruptor automático de tiempo inverso	250	9
13	Con Rotor Bobinado	Fusible sin retardo de tiempo	150	9
14	Con Rotor Bobinado	Fusible con retardo de tiempo	150	9
15	Con Rotor Bobinado	Interruptor automático con retardo de tiempo	800	9
16	Con Rotor Bobinado	Interruptor automático de tiempo inverso	150	9
\.


--
-- Name: PORCENTAJE_MOTORES_TRIFASICOS_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"PORCENTAJE_MOTORES_TRIFASICOS_codigo_seq"', 16, true);


--
-- Data for Name: POTENCIA; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "POTENCIA" (codigo, unidad_codigo, cantidad) FROM stdin;
1	3	250
2	3	700
3	3	800
4	3	1000
5	3	1100
6	3	1200
7	3	1500
8	3	1600
9	3	1850
10	3	1900
11	3	2000
12	3	2400
13	3	2900
14	3	3000
15	3	3600
16	3	4500
17	3	5000
18	3	5500
19	3	6000
20	3	8000
21	3	9000
22	3	12000
23	3	14000
24	3	15000
25	3	16000
26	3	19000
27	3	20000
28	3	30000
29	3	40000
30	3	50000
31	3	60000
\.


--
-- Name: POTENCIA_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"POTENCIA_codigo_seq"', 31, true);


--
-- Data for Name: RESISTENCIA_REACTANCIA; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "RESISTENCIA_REACTANCIA" (codigo, calibre_codigo, ducto_codigo, valor_codigo, material_codigo, tipo) FROM stdin;
1	11	1	7	1	R
2	11	2	7	1	R
3	11	3	7	1	R
4	10	1	1	1	R
5	10	2	1	1	R
6	10	3	1	1	R
7	9	1	3	1	R
8	9	2	3	1	R
9	9	3	3	1	R
10	8	1	5	1	R
11	8	2	5	1	R
12	8	3	5	1	R
13	7	1	9	1	R
14	7	2	9	1	R
15	7	3	9	1	R
16	6	1	11	1	R
17	6	2	11	1	R
18	6	3	11	1	R
19	5	1	16	1	R
20	5	2	15	1	R
21	5	3	15	1	R
22	1	1	53	1	R
23	1	2	53	1	R
24	1	3	44	1	R
25	2	1	22	1	R
26	2	2	22	1	R
27	2	3	22	1	R
28	3	1	28	1	R
29	3	2	26	1	R
30	3	3	27	1	R
31	4	1	39	1	R
32	4	2	34	1	R
33	4	3	38	1	R
34	14	1	46	1	R
35	14	2	43	1	R
36	14	3	45	1	R
37	15	1	53	1	R
38	15	2	49	1	R
39	15	3	52	1	R
40	16	1	59	1	R
41	16	2	54	1	R
42	16	3	58	1	R
43	18	1	62	1	R
44	18	2	60	1	R
45	18	3	61	1	R
46	10	1	6	2	R
47	10	2	6	2	R
48	10	3	6	2	R
49	9	1	1	2	R
50	9	2	1	2	R
51	9	3	1	2	R
52	8	1	5	2	R
53	8	2	5	2	R
54	8	3	5	2	R
55	7	1	4	2	R
56	7	2	4	2	R
57	7	3	4	2	R
58	6	1	8	2	R
59	6	2	8	2	R
60	6	3	8	2	R
61	5	1	10	2	R
62	5	2	10	2	R
63	5	3	10	2	R
64	1	1	15	2	R
65	1	2	14	2	R
66	1	3	15	2	R
67	2	1	17	2	R
68	2	2	17	2	R
69	2	3	17	2	R
70	3	1	19	2	R
71	3	2	19	2	R
72	3	3	19	2	R
73	4	1	22	2	R
74	4	2	21	2	R
75	4	3	22	2	R
76	14	1	25	2	R
77	14	2	23	2	R
78	14	3	24	2	R
79	15	1	32	2	R
80	15	2	29	2	R
81	15	3	31	2	R
82	16	1	40	2	R
83	16	2	35	2	R
84	16	3	38	2	R
85	18	1	54	2	R
86	18	2	50	2	R
87	18	3	52	2	R
88	19	1	72	1	R
89	19	2	75	1	R
90	19	3	78	1	R
91	20	1	73	1	R
92	20	2	76	1	R
93	20	3	79	1	R
94	21	1	74	1	R
95	21	2	77	1	R
96	21	3	80	1	R
97	19	1	81	2	R
98	19	2	84	2	R
99	19	3	55	2	R
100	20	1	82	2	R
101	20	2	81	2	R
102	20	3	57	2	R
103	21	1	83	2	R
104	21	2	85	2	R
105	21	3	58	2	R
106	11	1	42	\N	T
107	11	2	42	\N	T
108	11	3	30	\N	T
109	10	1	45	\N	T
110	10	2	45	\N	T
111	10	3	33	\N	T
112	9	1	48	\N	T
113	9	2	48	\N	T
114	9	3	38	\N	T
115	8	1	46	\N	T
116	8	2	46	\N	T
117	8	3	36	\N	T
118	7	1	47	\N	T
119	7	2	47	\N	T
120	7	3	37	\N	T
121	6	1	50	\N	T
122	6	2	50	\N	T
123	6	3	43	\N	T
124	5	1	52	\N	T
125	5	2	52	\N	T
126	5	3	43	\N	T
127	1	1	53	\N	T
128	1	2	53	\N	T
129	1	3	44	\N	T
130	2	1	54	\N	T
131	2	2	54	\N	T
132	2	3	45	\N	T
133	3	1	55	\N	T
134	3	2	55	\N	T
135	3	3	46	\N	T
136	4	1	56	\N	T
137	4	2	56	\N	T
138	4	3	47	\N	T
139	14	1	56	\N	T
140	14	2	56	\N	T
141	14	3	46	\N	T
142	15	1	56	\N	T
143	15	2	56	\N	T
144	15	3	47	\N	T
145	16	1	57	\N	T
146	16	2	57	\N	T
147	16	3	48	\N	T
148	18	1	58	\N	T
149	18	2	58	\N	T
150	18	3	50	\N	T
151	19	1	63	\N	T
152	19	2	66	\N	T
153	19	3	69	\N	T
154	20	1	64	\N	T
155	20	2	67	\N	T
156	20	3	70	\N	T
157	21	1	65	\N	T
158	21	2	68	\N	T
159	21	3	71	\N	T
\.


--
-- Name: RESISTENCIA_REACTANCIA_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"RESISTENCIA_REACTANCIA_codigo_seq"', 159, true);


--
-- Data for Name: TEMPERATURA; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "TEMPERATURA" (codigo, unidad_codigo, cantidad) FROM stdin;
1	2	60
2	2	75
\.


--
-- Name: TEMPERATURA_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"TEMPERATURA_codigo_seq"', 2, true);


--
-- Data for Name: TIPO_CARGA; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "TIPO_CARGA" (codigo, nombre, tipo) FROM stdin;
1	Aire acondicionado	P
2	Calentador	P
3	Cocina eléctrica	C
4	Cocina	P
5	Estufa	P
6	Horno	P
7	Motor	I
8	Plancha	P
9	Pulidora	P
10	Refrigerador	P
11	Secadora	C
12	Iluminaria y Toma Corriente	P
13	Lavaplatos	P
14	Trituradora	P
\.


--
-- Name: TIPO_CARGA_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"TIPO_CARGA_codigo_seq"', 14, true);


--
-- Data for Name: TIPO_DE_INSTALACION; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "TIPO_DE_INSTALACION" (codigo, nombre) FROM stdin;
1	Vivienda unifamiliar
\.


--
-- Name: TIPO_DE_INSTALACION_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"TIPO_DE_INSTALACION_codigo_seq"', 1, true);


--
-- Data for Name: TIPO_FASE; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "TIPO_FASE" (codigo, nombre) FROM stdin;
1	Monofásico
2	Trifásico
\.


--
-- Name: TIPO_FASE_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"TIPO_FASE_codigo_seq"', 2, true);


--
-- Data for Name: TUBERIAS; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "TUBERIAS" (codigo, tamano, dos_conductores, tres_cuatro_conductores) FROM stdin;
1	1/2	61	78
2	3/4	106	137
3	1	172	222
4	1 1/4	300	387
5	1 1/2	407	526
6	2	671	866
7	2 1/2	1173	1513
8	3	1767	2280
9	3 1/2	2310	2980
10	4	2951	3808
\.


--
-- Name: TUBERIAS_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"TUBERIAS_codigo_seq"', 10, true);


--
-- Data for Name: UNIDAD; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "UNIDAD" (codigo, nombre) FROM stdin;
1	BTU
2	°C
3	W
4	A
5	KVA
6	V
7	m/s
8	HP
9	%
\.


--
-- Name: UNIDAD_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"UNIDAD_codigo_seq"', 9, true);


--
-- Data for Name: VALOR; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "VALOR" (codigo, valor) FROM stdin;
1	6.60000
2	4.30000
3	3.90000
4	2.66000
5	2.56000
6	10.50000
7	10.20000
8	1.67000
9	1.61000
10	1.05000
11	1.02000
12	0.85000
13	0.82000
14	0.69000
15	0.66000
16	0.62000
17	0.52000
18	0.49000
19	0.43000
20	0.39000
21	0.36000
22	0.33000
23	0.29500
24	0.28200
25	0.27900
26	0.26900
27	0.25900
28	0.25300
29	0.24900
30	0.24000
31	0.23600
32	0.23300
33	0.22300
34	0.22000
35	0.21700
36	0.21300
37	0.21000
38	0.20700
39	0.20300
40	0.20000
41	0.19700
42	0.19000
43	0.18700
44	0.18000
45	0.17700
46	0.17100
47	0.16700
48	0.16400
49	0.16100
50	0.15700
51	0.15100
52	0.14800
53	0.14400
54	0.14100
55	0.13800
56	0.13500
57	0.13100
58	0.12800
59	0.12500
60	0.10500
61	0.09500
62	0.08900
63	0.10560
64	0.10430
65	0.10300
66	0.11020
67	0.10890
68	0.10790
69	0.11480
70	0.11320
71	0.11230
72	0.07100
73	0.06200
74	0.05800
75	0.07400
76	0.06500
77	0.06100
78	0.07700
79	0.06700
80	0.06300
81	0.12700
82	0.12100
83	0.11800
84	0.13200
85	0.12400
\.


--
-- Name: VALOR_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"VALOR_codigo_seq"', 85, true);


--
-- Data for Name: VELOCIDAD; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "VELOCIDAD" (codigo, velocidad, unidad_codigo) FROM stdin;
1	1	7
2	1.29999995	7
3	1.5	7
4	1.79999995	7
5	2	7
\.


--
-- Name: VELOCIDAD_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"VELOCIDAD_codigo_seq"', 5, true);


--
-- Data for Name: VOLTAJE; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "VOLTAJE" (codigo, voltaje, unidad_codigo) FROM stdin;
1	110	6
2	115	6
3	120	6
4	208	6
5	220	6
6	240	6
7	440	6
\.


--
-- Data for Name: VOLTAJE_MOTORES_TRIFASICOS; Type: TABLE DATA; Schema: maestros; Owner: postgres
--

COPY "VOLTAJE_MOTORES_TRIFASICOS" (codigo, voltaje, unidad_codigo) FROM stdin;
1	115	6
2	200	6
3	208	6
4	230	6
5	460	6
6	575	6
7	2300	6
\.


--
-- Name: VOLTAJE_MOTORES_TRIFASICOS_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"VOLTAJE_MOTORES_TRIFASICOS_codigo_seq"', 7, true);


--
-- Name: VOLTAJE_codigo_seq; Type: SEQUENCE SET; Schema: maestros; Owner: postgres
--

SELECT pg_catalog.setval('"VOLTAJE_codigo_seq"', 7, true);


SET search_path = negocio, pg_catalog;

--
-- Data for Name: ALIMENTADOR_PRINCIPAL; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "ALIMENTADOR_PRINCIPAL" (proyecto_codigo, proyecto_tipo, tipo_carga_codigo, cantidad, intensidad, potencia, neutro) FROM stdin;
43	1	12	0	0	353650	0
43	1	2	0	0	51400	51400
43	1	10	0	0	31500	31500
43	1	11	44	0	0	0
43	1	1	0	0	147200	0
43	1	3	2	0	0	0
43	1	13	0	0	497.32835999999998	497.32835999999998
43	1	14	0	0	497.32835999999998	497.32835999999998
\.


--
-- Data for Name: AREA; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "AREA" (codigo, proyecto_codigo, proyecto_tipo_instalacion, nombre, potencia_total, neutro, cantidad) FROM stdin;
138	43	1	Concerje	5574.50000	5574.50000	1
137	43	1	Apartamentos	14687.00000	9987.00000	42
139	43	1	Penthouse	28096.82836	17796.82836	2
\.


--
-- Data for Name: AREA_ILUMINARIA_TOMA_CORRIENTES; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "AREA_ILUMINARIA_TOMA_CORRIENTES" (codigo, area_codigo, calibres_codigo, voltaje_codigo, fase_codigo, tipo, area_cantidad, constante, factor_potencia, calibre_codigo, interruptor_codigo, acometida, longitud, ducto_codigo, angulo, calibre_a_usar, circuito_ramal, tuberia, tuberia_material) FROM stdin;
470	137	1	3	1	I	140.00000	25	1	11	2	A	0.10000	1	0.00000	2 Cables #14 Cu +TW 1x20.0	2	1 Φ 1/2" EMT	EMT
471	137	1	3	1	T	24.00000	180	1	11	1	A	0.10000	1	0.00000	2 Cables #14 Cu +TW 1x15.0	3	1 Φ 1/2" EMT	EMT
472	137	3	4	2	A	0.00000	1	0.949999988	9	4	A	0.10000	1	0.00000	2 Cables #10 Cu +TW 2x30.0	0	1 Φ 1/2" EMT	EMT
473	137	3	4	2	N	0.00000	1	0.949999988	9	4	A	0.10000	1	0.00000	1 Cable #10 Cu +TW	0	1 Φ 1/2" EMT	EMT
474	138	1	3	1	I	70.00000	25	1	11	2	A	0.10000	1	0.00000	2 Cables #14 Cu +TW 1x20.0	1	1 Φ 1/2" EMT	EMT
475	138	1	3	1	T	24.00000	180	1	11	1	A	0.10000	1	0.00000	2 Cables #14 Cu +TW 1x15.0	3	1 Φ 1/2" EMT	EMT
476	138	2	4	2	A	0.00000	1	0.949999988	10	3	A	0.10000	1	0.00000	2 Cables #12 Cu +TW 2x25.0	0	1 Φ 1/2" EMT	EMT
477	138	2	4	2	N	0.00000	1	0.949999988	10	3	A	0.10000	1	0.00000	1 Cable #12 Cu +TW	0	1 Φ 1/2" EMT	EMT
478	139	1	3	1	I	210.00000	25	1	11	2	A	0.10000	1	0.00000	2 Cables #14 Cu +TW 1x20.0	3	1 Φ 1/2" EMT	EMT
479	139	1	3	1	T	24.00000	180	1	11	1	A	0.10000	1	0.00000	2 Cables #14 Cu +TW 1x15.0	3	1 Φ 1/2" EMT	EMT
480	139	4	4	2	A	0.00000	1	0.949999988	8	6	A	0.10000	1	0.00000	2 Cables #8 Cu +TW 2x40.0	0	1 Φ 1/2" EMT	EMT
481	139	4	4	2	N	0.00000	1	0.949999988	8	6	A	0.10000	1	0.00000	1 Cable #8 Cu +TW	0	1 Φ 1/2" EMT	EMT
\.


--
-- Data for Name: AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA" (area_iluminaria_toma_corriente_codigo, resistencia_reactancia_codigo, tipo) FROM stdin;
\.


--
-- Name: AREA_ILUMINARIA_TOMA_CORRIENTES_codigo_seq; Type: SEQUENCE SET; Schema: negocio; Owner: postgres
--

SELECT pg_catalog.setval('"AREA_ILUMINARIA_TOMA_CORRIENTES_codigo_seq"', 481, true);


--
-- Name: AREA_codigo_seq; Type: SEQUENCE SET; Schema: negocio; Owner: postgres
--

SELECT pg_catalog.setval('"AREA_codigo_seq"', 139, true);


--
-- Data for Name: CARGAS_EN_AREAS; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "CARGAS_EN_AREAS" (codigo_carga, codigo_area, potencia, cantidad, calibre_fase, calibre_neutro, calibre_tierra, fase_codigo, tuberia, tuberia_material, cantidad_en_voltaje) FROM stdin;
10	137	1100.00000	1	1 Cable #14 Cu +TW 1x15.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	1	1 Φ 1/2" EMT	EMT	120.00000
9	138	800.00000	1	1 Cable #14 Cu +TW 1x15.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	1	1 Φ 1/2" EMT	EMT	120.00000
10	139	1100.00000	2	1 Cable #14 Cu +TW 1x15.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	1	1 Φ 1/2" EMT	EMT	120.00000
21	137	700.00000	1	1 Cable #14 Cu +TW 1x15.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	1	1 Φ 1/2" EMT	EMT	120.00000
21	138	700.00000	1	1 Cable #14 Cu +TW 1x15.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	1	1 Φ 1/2" EMT	EMT	120.00000
21	139	700.00000	1	1 Cable #14 Cu +TW 1x15.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	1	1 Φ 1/2" EMT	EMT	120.00000
22	137	5000.00000	1	2 Cables #8 Cu +TW 2x40.0	1 Cable #12 Cu +TW	1 Cable #10 Cu +TW	2	1 Φ 1/2" EMT	EMT	208.00000
22	139	5000.00000	1	2 Cables #8 Cu +TW 2x40.0	1 Cable #12 Cu +TW	1 Cable #10 Cu +TW	2	1 Φ 1/2" EMT	EMT	208.00000
2	137	1600.00000	2	2 Cables #14 Cu +TW 2x15.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	2	1 Φ 1/2" EMT	EMT	208.00000
2	139	1600.00000	4	2 Cables #14 Cu +TW 2x15.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	2	1 Φ 1/2" EMT	EMT	208.00000
12	139	8000.00000	1	2 Cables #6 Cu +TW 2x50.0	1 Cable #6 Cu +TW	1 Cable #10 Cu +TW	2	1 Φ 3/4" EMT	EMT	208.00000
17	139	248.66418	1	1 Cable #14 Cu +TW 1x20.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	1	1 Φ 1/2" EMT	EMT	120.00000
16	139	248.66418	1	1 Cable #14 Cu +TW 1x20.0	1 Cable #14 Cu +TW	1 Cable #14 Cu +TW	1	1 Φ 1/2" EMT	EMT	120.00000
\.


--
-- Data for Name: CIRCUITO_DE_ILUMINACION; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "CIRCUITO_DE_ILUMINACION" (codigo, proyecto_codigo, proyecto_tipo_instalacion, calibre_fase_neutro, tuberia, descripcion, intensidad_total) FROM stdin;
22	43	1	4 Cables #14 Cu +THW 3x20.0	1 Φ 1/2" EMT	iluminaria	12.49075
\.


--
-- Name: CIRCUITO_DE_ILUMINACION_codigo_seq; Type: SEQUENCE SET; Schema: negocio; Owner: postgres
--

SELECT pg_catalog.setval('"CIRCUITO_DE_ILUMINACION_codigo_seq"', 22, true);


--
-- Data for Name: CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL" (proyecto_codigo, proyecto_tipo, fase, neutro, tierra, tuberia_fase, tuberia_neutro, conductor_fase, conductor_neutro) FROM stdin;
43	1	3 Cables #700 Cu TTU 3x1600.0	2 Cables #600 Cu TTU	1 Cable #4/0 Cu TTU	1 Φ 3 1/2" EMT	1 Φ 2 1/2" EMT	3	2
\.


--
-- Data for Name: ELEVADORES_EN_LA_INSTALACION; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "ELEVADORES_EN_LA_INSTALACION" (codigo, proyecto_codigo, proyecto_tipo_instalacion, elevador_codigo, calibres_codigo, cantidad) FROM stdin;
\.


--
-- Name: ELEVADORES_EN_LA_INSTALACION_codigo_seq; Type: SEQUENCE SET; Schema: negocio; Owner: postgres
--

SELECT pg_catalog.setval('"ELEVADORES_EN_LA_INSTALACION_codigo_seq"', 1, false);


--
-- Data for Name: ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS" (elevadores_instalacion_codigo, resistencia_reactancia_codigo, tipo) FROM stdin;
\.


--
-- Data for Name: MOTORES_EN_INSTALACION; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "MOTORES_EN_INSTALACION" (codigo, proyecto_codigo, proyecto_tipo_instalacion, descripcion, intensidad, cantidad, calibre_fase, calibre_neutro, calibre_tierra, tipo_fase_codigo, caballo_de_fuerza, breaker, tuberia, tuberia_material) FROM stdin;
83	43	1	ascensor	88	1	3 Cables #1/0 Cu +TW 3x225.0	1 Cable #1/0 Cu +TW	1 Cable #8 Cu +TW	2	30	225	1 Φ 2" EMT	EMT
84	43	1	hidro	24.199999999999999	1	3 Cables #10 Cu +THW 3x70.0	1 Cable #10 Cu +THW	1 Cable #10 Cu +THW	2	7 1/2	70	1 Φ 1/2" EMT	EMT
85	43	1	porton	4.5999999999999996	1	3 Cables #14 Cu +THW 3x15.0	1 Cable #14 Cu +THW	1 Cable #14 Cu +THW	2	1	15	1 Φ 1/2" EMT	EMT
\.


--
-- Name: MOTORES_EN_INSTALACION_codigo_seq; Type: SEQUENCE SET; Schema: negocio; Owner: postgres
--

SELECT pg_catalog.setval('"MOTORES_EN_INSTALACION_codigo_seq"', 85, true);


--
-- Data for Name: PROYECTO; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "PROYECTO" (usuario_nacionalidad, usuario_cedula, nombre, codigo, fecha_registro, potencia_total, tipo_de_instalacion_codigo, intensidad_motores, fase_motor, tierra_motor, tuberia_motor) FROM stdin;
V	19185244	Test	43	2015-11-18 21:35:23.417-04:30	0.00000	1	116.8	\N	\N	\N
\.


--
-- Name: PROYECTO_codigo_seq; Type: SEQUENCE SET; Schema: negocio; Owner: postgres
--

SELECT pg_catalog.setval('"PROYECTO_codigo_seq"', 43, true);


--
-- Data for Name: USUARIO; Type: TABLE DATA; Schema: negocio; Owner: postgres
--

COPY "USUARIO" (nacionalidad, cedula, nombre, apellido, direccion, nombre_usuario, estatus, clave_usuario, telefono, fecha_registro) FROM stdin;
V	19185244	Richard	David	Urb. Piedras Blancas	richard	t	\\x40bd001563085fc35165329ea1ff5c5ecbdbbeef	(0414)-(8504141)	2015-07-27 15:28:32.807-04:30
V	0	Administrador	Administrador	Administrador	administrador	t	\\xd033e22ae348aeb5660fc2140aec35850c4da997	\N	2015-08-01 13:26:32.572-04:30
V	19324511	erwis	alvarez	san francisco	erwis	t	\\x40bd001563085fc35165329ea1ff5c5ecbdbbeef	(0414)-(1595493)	2015-10-28 14:36:57.812-04:30
\.


SET search_path = maestros, pg_catalog;

--
-- Name: CABALLOS_DE_POTENCIA_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CABALLOS_DE_POTENCIAS"
    ADD CONSTRAINT "CABALLOS_DE_POTENCIA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: CABALLO_DE_FUERZA_TRIFASICO_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CABALLO_DE_FUERZA_TRIFASICO"
    ADD CONSTRAINT "CABALLO_DE_FUERZA_TRIFASICO_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: CABALLO_DE_POTENCIA_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CABALLO_DE_POTENCIA"
    ADD CONSTRAINT "CABALLO_DE_POTENCIA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: CABALLO_DE_POTENCIA_UNQ_NOMBRE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CABALLO_DE_POTENCIA"
    ADD CONSTRAINT "CABALLO_DE_POTENCIA_UNQ_NOMBRE" UNIQUE (nombre);


--
-- Name: CABLE_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CABLE"
    ADD CONSTRAINT "CABLE_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: CABLE_UNQ_NOMBRE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CABLE"
    ADD CONSTRAINT "CABLE_UNQ_NOMBRE" UNIQUE (nombre);


--
-- Name: CALIBRES_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CALIBRES"
    ADD CONSTRAINT "CALIBRES_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: CALIBRE_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CALIBRE"
    ADD CONSTRAINT "CALIBRE_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: CALIBRE_UNQ_NOMBRE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CALIBRE"
    ADD CONSTRAINT "CALIBRE_UNQ_NOMBRE" UNIQUE (nombre);


--
-- Name: CARGA_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CARGA"
    ADD CONSTRAINT "CARGA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: CONDUCTORES_DE_TIERRA_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CONDUCTORES_DE_TIERRA"
    ADD CONSTRAINT "CONDUCTORES_DE_TIERRA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: DUCTO_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "DUCTO"
    ADD CONSTRAINT "DUCTO_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: DUCTO_UNQ_NOMBRE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "DUCTO"
    ADD CONSTRAINT "DUCTO_UNQ_NOMBRE" UNIQUE (nombre);


--
-- Name: ELEVADOR_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "ELEVADOR"
    ADD CONSTRAINT "ELEVADOR_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: ENERGIA_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "ENERGIA"
    ADD CONSTRAINT "ENERGIA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: ENERGIA_UNQ_CANTIDAD; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "ENERGIA"
    ADD CONSTRAINT "ENERGIA_UNQ_CANTIDAD" UNIQUE (cantidad);


--
-- Name: FASE_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "FASE"
    ADD CONSTRAINT "FASE_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: FASE_PORCENTAJE_MOTORES_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "FASE_PORCENTAJE_MOTORES"
    ADD CONSTRAINT "FASE_PORCENTAJE_MOTORES_PK_CODIGO" PRIMARY KEY (fase_codigo, porcentaje_motores_codigo);


--
-- Name: FASE_UNQ_NOMBRE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "FASE"
    ADD CONSTRAINT "FASE_UNQ_NOMBRE" UNIQUE (nombre);


--
-- Name: INTENSIDAD_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "INTENSIDAD"
    ADD CONSTRAINT "INTENSIDAD_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: INTERRUPTOR_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "INTERRUPTOR"
    ADD CONSTRAINT "INTERRUPTOR_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: INTERRUPTOR_UNQ_CAPACIDAD; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "INTERRUPTOR"
    ADD CONSTRAINT "INTERRUPTOR_UNQ_CAPACIDAD" UNIQUE (capacidad);


--
-- Name: MARCA_DE_CABLE_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "MARCA_DE_CABLE"
    ADD CONSTRAINT "MARCA_DE_CABLE_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: MATERIAL_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "MATERIAL"
    ADD CONSTRAINT "MATERIAL_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: MATERIAL_UNQ_NOMBRE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "MATERIAL"
    ADD CONSTRAINT "MATERIAL_UNQ_NOMBRE" UNIQUE (nombre);


--
-- Name: MOTORES_TRIFASICOS_DE_INDUCCION_PK; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "MOTORES_TRIFASICOS_DE_INDUCCION"
    ADD CONSTRAINT "MOTORES_TRIFASICOS_DE_INDUCCION_PK" PRIMARY KEY (caballo_de_fuerza_trifasico_codigo, voltaje_motores_trifasicos_codigo);


--
-- Name: NUMERO_DE_PERSONAS_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "NUMERO_DE_PERSONAS"
    ADD CONSTRAINT "NUMERO_DE_PERSONAS_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: NUMERO_DE_PERSONAS_UNQ_CANTIDAD; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "NUMERO_DE_PERSONAS"
    ADD CONSTRAINT "NUMERO_DE_PERSONAS_UNQ_CANTIDAD" UNIQUE (cantidad);


--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "PORCENTAJE_MOTORES_MONOFASICOS"
    ADD CONSTRAINT "PORCENTAJE_MOTORES_MONOFASICOS_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS_UNQ_NOMBRE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "PORCENTAJE_MOTORES_MONOFASICOS"
    ADD CONSTRAINT "PORCENTAJE_MOTORES_MONOFASICOS_UNQ_NOMBRE" UNIQUE (nombre);


--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS_UNQ_PORCENTAJE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "PORCENTAJE_MOTORES_MONOFASICOS"
    ADD CONSTRAINT "PORCENTAJE_MOTORES_MONOFASICOS_UNQ_PORCENTAJE" UNIQUE (porcentaje);


--
-- Name: PORCENTAJE_MOTORES_TRIFASICOS_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "PORCENTAJE_MOTORES_TRIFASICOS"
    ADD CONSTRAINT "PORCENTAJE_MOTORES_TRIFASICOS_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: POTENCIA_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "POTENCIA"
    ADD CONSTRAINT "POTENCIA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: POTENCIA_UNQ_CANTIDAD; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "POTENCIA"
    ADD CONSTRAINT "POTENCIA_UNQ_CANTIDAD" UNIQUE (cantidad);


--
-- Name: RESISTENCIA_REACTANCIA_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "RESISTENCIA_REACTANCIA"
    ADD CONSTRAINT "RESISTENCIA_REACTANCIA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: TEMPERATURA_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "TEMPERATURA"
    ADD CONSTRAINT "TEMPERATURA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: TIPO_CARGA_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "TIPO_CARGA"
    ADD CONSTRAINT "TIPO_CARGA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: TIPO_DE_INSTALACION_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "TIPO_DE_INSTALACION"
    ADD CONSTRAINT "TIPO_DE_INSTALACION_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: TIPO_FASE_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "TIPO_FASE"
    ADD CONSTRAINT "TIPO_FASE_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: TUBERIAS_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "TUBERIAS"
    ADD CONSTRAINT "TUBERIAS_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: UNIDAD_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "UNIDAD"
    ADD CONSTRAINT "UNIDAD_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: UNIDAD_UNQ_NOMBRE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "UNIDAD"
    ADD CONSTRAINT "UNIDAD_UNQ_NOMBRE" UNIQUE (nombre);


--
-- Name: VALOR_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "VALOR"
    ADD CONSTRAINT "VALOR_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: VALOR_UNQ_VALOR; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "VALOR"
    ADD CONSTRAINT "VALOR_UNQ_VALOR" UNIQUE (codigo);


--
-- Name: VELOCIDAD_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "VELOCIDAD"
    ADD CONSTRAINT "VELOCIDAD_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: VELOCIDAD_UNQ_VELOCIDAD; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "VELOCIDAD"
    ADD CONSTRAINT "VELOCIDAD_UNQ_VELOCIDAD" UNIQUE (velocidad);


--
-- Name: VOLTAJE_MOTORES_TRIFASICOS_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "VOLTAJE_MOTORES_TRIFASICOS"
    ADD CONSTRAINT "VOLTAJE_MOTORES_TRIFASICOS_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: VOLTAJE_PK_CODIGO; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "VOLTAJE"
    ADD CONSTRAINT "VOLTAJE_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: VOLTAJE_UNQ_VOLTAJE; Type: CONSTRAINT; Schema: maestros; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "VOLTAJE"
    ADD CONSTRAINT "VOLTAJE_UNQ_VOLTAJE" UNIQUE (voltaje);


SET search_path = negocio, pg_catalog;

--
-- Name: ALIMENTADOR_PK; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "ALIMENTADOR_PRINCIPAL"
    ADD CONSTRAINT "ALIMENTADOR_PK" PRIMARY KEY (proyecto_codigo, proyecto_tipo, tipo_carga_codigo);


--
-- Name: AREA_I_O_T_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES"
    ADD CONSTRAINT "AREA_I_O_T_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA"
    ADD CONSTRAINT "AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_PK_CODIGO" PRIMARY KEY (area_iluminaria_toma_corriente_codigo, resistencia_reactancia_codigo);


--
-- Name: AREA_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "AREA"
    ADD CONSTRAINT "AREA_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: CARGAS_EN_AREAS_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CARGAS_EN_AREAS"
    ADD CONSTRAINT "CARGAS_EN_AREAS_PK_CODIGO" PRIMARY KEY (codigo_carga, codigo_area);


--
-- Name: CIRCUITO_DE_ILUMINACION_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CIRCUITO_DE_ILUMINACION"
    ADD CONSTRAINT "CIRCUITO_DE_ILUMINACION_PK_CODIGO" PRIMARY KEY (codigo, proyecto_codigo, proyecto_tipo_instalacion);


--
-- Name: CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL_PK; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL"
    ADD CONSTRAINT "CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL_PK" PRIMARY KEY (proyecto_codigo, proyecto_tipo);


--
-- Name: ELEVADORES_INSTALACION_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "ELEVADORES_EN_LA_INSTALACION"
    ADD CONSTRAINT "ELEVADORES_INSTALACION_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: ELEVADORES_INSTALACION_R_Y_RT_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS"
    ADD CONSTRAINT "ELEVADORES_INSTALACION_R_Y_RT_PK_CODIGO" PRIMARY KEY (elevadores_instalacion_codigo, resistencia_reactancia_codigo);


--
-- Name: MOTORES_EN_INSTALACION_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "MOTORES_EN_INSTALACION"
    ADD CONSTRAINT "MOTORES_EN_INSTALACION_PK_CODIGO" PRIMARY KEY (codigo);


--
-- Name: PROYECTO_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "PROYECTO"
    ADD CONSTRAINT "PROYECTO_PK_CODIGO" PRIMARY KEY (codigo, tipo_de_instalacion_codigo);


--
-- Name: USUARIO_PK_CODIGO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "USUARIO"
    ADD CONSTRAINT "USUARIO_PK_CODIGO" PRIMARY KEY (nacionalidad, cedula);


--
-- Name: USUARIO_UNQ_NOMBRE_USUARIO; Type: CONSTRAINT; Schema: negocio; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "USUARIO"
    ADD CONSTRAINT "USUARIO_UNQ_NOMBRE_USUARIO" UNIQUE (nombre_usuario);


SET search_path = maestros, pg_catalog;

--
-- Name: CABALLOS_DE_POTENCIA_IDX_FK_CABALLO_POTENCIA; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CABALLOS_DE_POTENCIA_IDX_FK_CABALLO_POTENCIA" ON "CABALLOS_DE_POTENCIAS" USING btree (caballo_de_potencia_codigo);


--
-- Name: CABALLOS_DE_POTENCIA_IDX_FK_INTENSIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CABALLOS_DE_POTENCIA_IDX_FK_INTENSIDAD" ON "CABALLOS_DE_POTENCIAS" USING btree (intensidad_codigo);


--
-- Name: CABALLOS_DE_POTENCIA_IDX_FK_VOLTAJE; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CABALLOS_DE_POTENCIA_IDX_FK_VOLTAJE" ON "CABALLOS_DE_POTENCIAS" USING btree (voltaje_codigo);


--
-- Name: CABALLOS_DE_POTENCIA_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CABALLOS_DE_POTENCIA_IDX_PK_CODIGO" ON "CABALLOS_DE_POTENCIAS" USING btree (codigo);


--
-- Name: CABALLO_DE_FUERZA_TRIFASICO_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CABALLO_DE_FUERZA_TRIFASICO_IDX_FK_UNIDAD" ON "CABALLO_DE_FUERZA_TRIFASICO" USING btree (unidad_codigo);


--
-- Name: CABALLO_DE_POTENCIA_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CABALLO_DE_POTENCIA_IDX_FK_UNIDAD" ON "CABALLO_DE_POTENCIA" USING btree (unidad_codigo);


--
-- Name: CABALLO_DE_POTENCIA_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CABALLO_DE_POTENCIA_IDX_PK_CODIGO" ON "CABALLO_DE_POTENCIA" USING btree (unidad_codigo);


--
-- Name: CABLE_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CABLE_IDX_PK_CODIGO" ON "CABLE" USING btree (codigo);


--
-- Name: CALIBRES_IDX_FK_CALIBRE; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CALIBRES_IDX_FK_CALIBRE" ON "CALIBRES" USING btree (calibre_codigo);


--
-- Name: CALIBRES_IDX_FK_INTENSIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CALIBRES_IDX_FK_INTENSIDAD" ON "CALIBRES" USING btree (intensidad_codigo);


--
-- Name: CALIBRES_IDX_FK_MATERIAL; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CALIBRES_IDX_FK_MATERIAL" ON "CALIBRES" USING btree (material_codigo);


--
-- Name: CALIBRES_IDX_FK_TEMPERATURA; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CALIBRES_IDX_FK_TEMPERATURA" ON "CALIBRES" USING btree (temperatura_codigo);


--
-- Name: CALIBRES_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CALIBRES_IDX_PK_CODIGO" ON "CALIBRES" USING btree (codigo);


--
-- Name: CALIBRE_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CALIBRE_IDX_PK_CODIGO" ON "CALIBRE" USING btree (codigo);


--
-- Name: CARGA_IDX_FK_ENERGIA; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CARGA_IDX_FK_ENERGIA" ON "CARGA" USING btree (energia_codigo);


--
-- Name: CARGA_IDX_FK_POTENCIA; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CARGA_IDX_FK_POTENCIA" ON "CARGA" USING btree (potencia_codigo);


--
-- Name: CARGA_IDX_FK_TIPO_CARGA_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CARGA_IDX_FK_TIPO_CARGA_CODIGO" ON "CARGA" USING btree (tipo_carga_codigo);


--
-- Name: CARGA_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CARGA_IDX_PK_CODIGO" ON "CARGA" USING btree (codigo);


--
-- Name: CONDUCTORES_DE_TIERRA_IDX_FK_CALIBRE; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CONDUCTORES_DE_TIERRA_IDX_FK_CALIBRE" ON "CONDUCTORES_DE_TIERRA" USING btree (calibre_codigo);


--
-- Name: CONDUCTORES_DE_TIERRA_IDX_FK_INTENSIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CONDUCTORES_DE_TIERRA_IDX_FK_INTENSIDAD" ON "CONDUCTORES_DE_TIERRA" USING btree (intensidad_codigo);


--
-- Name: CONDUCTORES_DE_TIERRA_IDX_FK_MATERIAL; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CONDUCTORES_DE_TIERRA_IDX_FK_MATERIAL" ON "CONDUCTORES_DE_TIERRA" USING btree (material_codigo);


--
-- Name: CONDUCTORES_DE_TIERRA_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "CONDUCTORES_DE_TIERRA_IDX_PK_CODIGO" ON "CONDUCTORES_DE_TIERRA" USING btree (codigo);


--
-- Name: DUCTO_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "DUCTO_IDX_PK_CODIGO" ON "DUCTO" USING btree (codigo);


--
-- Name: ELEVADOR_IDX_FK_NUMERO_DE_PERSONAS; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADOR_IDX_FK_NUMERO_DE_PERSONAS" ON "ELEVADOR" USING btree (numero_de_personas_codigo);


--
-- Name: ELEVADOR_IDX_FK_POTENCIA; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADOR_IDX_FK_POTENCIA" ON "ELEVADOR" USING btree (potencia_codigo);


--
-- Name: ELEVADOR_IDX_FK_VELOCIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADOR_IDX_FK_VELOCIDAD" ON "ELEVADOR" USING btree (velocidad_codigo);


--
-- Name: ELEVADOR_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADOR_IDX_PK_CODIGO" ON "ELEVADOR" USING btree (codigo);


--
-- Name: FASE_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "FASE_IDX_PK_CODIGO" ON "FASE" USING btree (codigo);


--
-- Name: FASE_IDX_VOLTAJE; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "FASE_IDX_VOLTAJE" ON "FASE" USING btree (nombre);


--
-- Name: FASE_PORCENTAJE_MOTORES_IDX_FK_FASE; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "FASE_PORCENTAJE_MOTORES_IDX_FK_FASE" ON "FASE_PORCENTAJE_MOTORES" USING btree (fase_codigo);


--
-- Name: FASE_PORCENTAJE_MOTORES_IDX_FK_PORCENTAJE_MOTORES; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "FASE_PORCENTAJE_MOTORES_IDX_FK_PORCENTAJE_MOTORES" ON "FASE_PORCENTAJE_MOTORES" USING btree (porcentaje_motores_codigo);


--
-- Name: FASE_PORCENTAJE_MOTORES_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "FASE_PORCENTAJE_MOTORES_IDX_PK_CODIGO" ON "FASE_PORCENTAJE_MOTORES" USING btree (fase_codigo, porcentaje_motores_codigo);


--
-- Name: INTENSIDAD_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "INTENSIDAD_IDX_FK_UNIDAD" ON "INTENSIDAD" USING btree (unidad_codigo);


--
-- Name: INTENSIDAD_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "INTENSIDAD_IDX_PK_CODIGO" ON "INTENSIDAD" USING btree (codigo);


--
-- Name: INTERRUPTOR_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "INTERRUPTOR_IDX_FK_UNIDAD" ON "INTERRUPTOR" USING btree (unidad_codigo);


--
-- Name: INTERRUPTOR_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "INTERRUPTOR_IDX_PK_CODIGO" ON "INTERRUPTOR" USING btree (codigo);


--
-- Name: MARCA_DE_CABLE_IDX_FK_CABLE; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "MARCA_DE_CABLE_IDX_FK_CABLE" ON "MARCA_DE_CABLE" USING btree (cable_codigo);


--
-- Name: MARCA_DE_CABLE_IDX_FK_MATERIAL; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "MARCA_DE_CABLE_IDX_FK_MATERIAL" ON "MARCA_DE_CABLE" USING btree (material_codigo);


--
-- Name: MARCA_DE_CABLE_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "MARCA_DE_CABLE_IDX_PK_CODIGO" ON "MARCA_DE_CABLE" USING btree (codigo);


--
-- Name: MARCA_DE_CABLE_IDX_TEMPERATURA; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "MARCA_DE_CABLE_IDX_TEMPERATURA" ON "MARCA_DE_CABLE" USING btree (temperatura_codigo);


--
-- Name: MATERIAL_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "MATERIAL_IDX_PK_CODIGO" ON "MATERIAL" USING btree (codigo);


--
-- Name: MOTORES_TRIFASICOS_DE_INDUCCION_IDX_FK_HP; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "MOTORES_TRIFASICOS_DE_INDUCCION_IDX_FK_HP" ON "MOTORES_TRIFASICOS_DE_INDUCCION" USING btree (caballo_de_fuerza_trifasico_codigo);


--
-- Name: MOTORES_TRIFASICOS_DE_INDUCCION_IDX_FK_VOLTAJE; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "MOTORES_TRIFASICOS_DE_INDUCCION_IDX_FK_VOLTAJE" ON "MOTORES_TRIFASICOS_DE_INDUCCION" USING btree (voltaje_motores_trifasicos_codigo);


--
-- Name: NUMERO_DE_PERSONAS_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "NUMERO_DE_PERSONAS_IDX_PK_CODIGO" ON "NUMERO_DE_PERSONAS" USING btree (codigo);


--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "PORCENTAJE_MOTORES_MONOFASICOS_IDX_FK_UNIDAD" ON "PORCENTAJE_MOTORES_MONOFASICOS" USING btree (unidad_codigo);


--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "PORCENTAJE_MOTORES_MONOFASICOS_IDX_PK_CODIGO" ON "PORCENTAJE_MOTORES_MONOFASICOS" USING btree (codigo);


--
-- Name: PORCENTAJE_MOTORES_TRIFASICOS_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "PORCENTAJE_MOTORES_TRIFASICOS_IDX_FK_UNIDAD" ON "PORCENTAJE_MOTORES_TRIFASICOS" USING btree (unidad_codigo);


--
-- Name: PORCENTAJE_MOTORES_TRIFASICOS_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "PORCENTAJE_MOTORES_TRIFASICOS_IDX_PK_CODIGO" ON "PORCENTAJE_MOTORES_TRIFASICOS" USING btree (codigo);


--
-- Name: POTENCIA_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "POTENCIA_IDX_FK_UNIDAD" ON "POTENCIA" USING btree (unidad_codigo);


--
-- Name: POTENCIA_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "POTENCIA_IDX_PK_CODIGO" ON "POTENCIA" USING btree (codigo);


--
-- Name: RESISTENCIA_REACTANCIA_IDX_FK_CALIBRE; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "RESISTENCIA_REACTANCIA_IDX_FK_CALIBRE" ON "RESISTENCIA_REACTANCIA" USING btree (calibre_codigo);


--
-- Name: RESISTENCIA_REACTANCIA_IDX_FK_DUCTO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "RESISTENCIA_REACTANCIA_IDX_FK_DUCTO" ON "RESISTENCIA_REACTANCIA" USING btree (ducto_codigo);


--
-- Name: RESISTENCIA_REACTANCIA_IDX_FK_MATERIAL; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "RESISTENCIA_REACTANCIA_IDX_FK_MATERIAL" ON "RESISTENCIA_REACTANCIA" USING btree (material_codigo);


--
-- Name: RESISTENCIA_REACTANCIA_IDX_FK_VALOR; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "RESISTENCIA_REACTANCIA_IDX_FK_VALOR" ON "RESISTENCIA_REACTANCIA" USING btree (valor_codigo);


--
-- Name: RESISTENCIA_REACTANCIA_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "RESISTENCIA_REACTANCIA_IDX_PK_CODIGO" ON "RESISTENCIA_REACTANCIA" USING btree (codigo);


--
-- Name: RESISTENCIA_REACTANCIA_IDX_TIPO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "RESISTENCIA_REACTANCIA_IDX_TIPO" ON "RESISTENCIA_REACTANCIA" USING btree (tipo);


--
-- Name: TEMPERATURA_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "TEMPERATURA_IDX_FK_UNIDAD" ON "TEMPERATURA" USING btree (unidad_codigo);


--
-- Name: TEMPERATURA_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "TEMPERATURA_IDX_PK_CODIGO" ON "TEMPERATURA" USING btree (codigo);


--
-- Name: TIPO_CARGA_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "TIPO_CARGA_IDX_PK_CODIGO" ON "TIPO_CARGA" USING btree (codigo);


--
-- Name: TIPO_DE_INSTALACION_IDX_NOMBRE; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "TIPO_DE_INSTALACION_IDX_NOMBRE" ON "TIPO_DE_INSTALACION" USING btree (nombre);


--
-- Name: TIPO_INSTALACION_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "TIPO_INSTALACION_IDX_PK_CODIGO" ON "TIPO_DE_INSTALACION" USING btree (codigo);


--
-- Name: UNIDAD_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "UNIDAD_IDX_PK_CODIGO" ON "UNIDAD" USING btree (codigo);


--
-- Name: VALOR_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "VALOR_IDX_PK_CODIGO" ON "VALOR" USING btree (codigo);


--
-- Name: VELOCIDAD_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "VELOCIDAD_IDX_FK_UNIDAD" ON "VELOCIDAD" USING btree (unidad_codigo);


--
-- Name: VELOCIDAD_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "VELOCIDAD_IDX_PK_CODIGO" ON "VELOCIDAD" USING btree (codigo);


--
-- Name: VOLTAJE_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "VOLTAJE_IDX_FK_UNIDAD" ON "VOLTAJE" USING btree (unidad_codigo);


--
-- Name: VOLTAJE_IDX_PK_CODIGO; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "VOLTAJE_IDX_PK_CODIGO" ON "VOLTAJE" USING btree (codigo);


--
-- Name: VOLTAJE_MOTORES_TRIFASICOS_IDX_FK_UNIDAD; Type: INDEX; Schema: maestros; Owner: postgres; Tablespace: 
--

CREATE INDEX "VOLTAJE_MOTORES_TRIFASICOS_IDX_FK_UNIDAD" ON "VOLTAJE_MOTORES_TRIFASICOS" USING btree (unidad_codigo);


SET search_path = negocio, pg_catalog;

--
-- Name: ALIMENTADOR_IDX_FK_PROYECTO_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "ALIMENTADOR_IDX_FK_PROYECTO_CODIGO" ON "ALIMENTADOR_PRINCIPAL" USING btree (proyecto_codigo, proyecto_tipo);


--
-- Name: ALIMENTADOR_IDX_FK_TIPO_CARGA_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "ALIMENTADOR_IDX_FK_TIPO_CARGA_CODIGO" ON "ALIMENTADOR_PRINCIPAL" USING btree (tipo_carga_codigo);


--
-- Name: AREA_IDX_FK_PROYECTO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_IDX_FK_PROYECTO" ON "AREA" USING btree (proyecto_codigo, proyecto_tipo_instalacion);


--
-- Name: AREA_IDX_PK_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_IDX_PK_CODIGO" ON "AREA" USING btree (codigo);


--
-- Name: AREA_I_O_T_IDX_FK_AREA; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_O_T_IDX_FK_AREA" ON "AREA_ILUMINARIA_TOMA_CORRIENTES" USING btree (area_codigo);


--
-- Name: AREA_I_O_T_IDX_FK_CALIBRE; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_O_T_IDX_FK_CALIBRE" ON "AREA_ILUMINARIA_TOMA_CORRIENTES" USING btree (calibre_codigo);


--
-- Name: AREA_I_O_T_IDX_FK_CALIBRES; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_O_T_IDX_FK_CALIBRES" ON "AREA_ILUMINARIA_TOMA_CORRIENTES" USING btree (calibres_codigo);


--
-- Name: AREA_I_O_T_IDX_FK_DUCTO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_O_T_IDX_FK_DUCTO" ON "AREA_ILUMINARIA_TOMA_CORRIENTES" USING btree (ducto_codigo);


--
-- Name: AREA_I_O_T_IDX_FK_FASE; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_O_T_IDX_FK_FASE" ON "AREA_ILUMINARIA_TOMA_CORRIENTES" USING btree (fase_codigo);


--
-- Name: AREA_I_O_T_IDX_FK_INTERRUPTOR; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_O_T_IDX_FK_INTERRUPTOR" ON "AREA_ILUMINARIA_TOMA_CORRIENTES" USING btree (interruptor_codigo);


--
-- Name: AREA_I_O_T_IDX_FK_VOLTAJE; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_O_T_IDX_FK_VOLTAJE" ON "AREA_ILUMINARIA_TOMA_CORRIENTES" USING btree (voltaje_codigo);


--
-- Name: AREA_I_O_T_IDX_PK_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_O_T_IDX_PK_CODIGO" ON "AREA_ILUMINARIA_TOMA_CORRIENTES" USING btree (codigo);


--
-- Name: AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_IDX_FK_A_I_T; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_IDX_FK_A_I_T" ON "AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA" USING btree (area_iluminaria_toma_corriente_codigo);


--
-- Name: AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_IDX_FK_R_X; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_IDX_FK_R_X" ON "AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA" USING btree (resistencia_reactancia_codigo);


--
-- Name: AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_IDX_PK_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_IDX_PK_CODIGO" ON "AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA" USING btree (area_iluminaria_toma_corriente_codigo, resistencia_reactancia_codigo);


--
-- Name: CARGAS_EN_AREAS_IDX_FK_AREA; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "CARGAS_EN_AREAS_IDX_FK_AREA" ON "CARGAS_EN_AREAS" USING btree (codigo_area);


--
-- Name: CARGAS_EN_AREAS_IDX_FK_CARGA; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "CARGAS_EN_AREAS_IDX_FK_CARGA" ON "CARGAS_EN_AREAS" USING btree (codigo_carga);


--
-- Name: CARGAS_EN_AREAS_IDX_FK_FASE; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "CARGAS_EN_AREAS_IDX_FK_FASE" ON "CARGAS_EN_AREAS" USING btree (fase_codigo);


--
-- Name: CARGAS_EN_AREAS_IDX_PK_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "CARGAS_EN_AREAS_IDX_PK_CODIGO" ON "CARGAS_EN_AREAS" USING btree (codigo_area, codigo_carga);


--
-- Name: CIRCUITO_DE_ILUMINACION_IDX_FK_PROYECTO_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "CIRCUITO_DE_ILUMINACION_IDX_FK_PROYECTO_CODIGO" ON "CIRCUITO_DE_ILUMINACION" USING btree (proyecto_codigo, proyecto_tipo_instalacion);


--
-- Name: CIRCUITO_DE_ILUMINACION_IDX_FK_PTOYECTO_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "CIRCUITO_DE_ILUMINACION_IDX_FK_PTOYECTO_CODIGO" ON "CIRCUITO_DE_ILUMINACION" USING btree (proyecto_codigo, proyecto_tipo_instalacion);


--
-- Name: CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL_FK_IDX; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL_FK_IDX" ON "CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL" USING btree (proyecto_codigo, proyecto_tipo);


--
-- Name: CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL_IDX_PK; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL_IDX_PK" ON "CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL" USING btree (proyecto_codigo, proyecto_tipo);


--
-- Name: ELEVADORES_INSTALACION_IDX_FK_CALIBRES; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADORES_INSTALACION_IDX_FK_CALIBRES" ON "ELEVADORES_EN_LA_INSTALACION" USING btree (calibres_codigo);


--
-- Name: ELEVADORES_INSTALACION_IDX_FK_ELEVADOR; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADORES_INSTALACION_IDX_FK_ELEVADOR" ON "ELEVADORES_EN_LA_INSTALACION" USING btree (elevador_codigo);


--
-- Name: ELEVADORES_INSTALACION_IDX_FK_PROYECTO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADORES_INSTALACION_IDX_FK_PROYECTO" ON "ELEVADORES_EN_LA_INSTALACION" USING btree (proyecto_codigo, proyecto_tipo_instalacion);


--
-- Name: ELEVADORES_INSTALACION_IDX_PK_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADORES_INSTALACION_IDX_PK_CODIGO" ON "ELEVADORES_EN_LA_INSTALACION" USING btree (codigo);


--
-- Name: ELEVADORES_INSTALACION_R_Y_RT_IDX_FK_ELEVADORES_INSTALACION; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADORES_INSTALACION_R_Y_RT_IDX_FK_ELEVADORES_INSTALACION" ON "ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS" USING btree (elevadores_instalacion_codigo);


--
-- Name: ELEVADORES_INSTALACION_R_Y_RT_IDX_FK_RESISTENCIA_REACTANCIA; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADORES_INSTALACION_R_Y_RT_IDX_FK_RESISTENCIA_REACTANCIA" ON "ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS" USING btree (resistencia_reactancia_codigo);


--
-- Name: ELEVADORES_INSTALACION_R_Y_RT_IDX_PK_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "ELEVADORES_INSTALACION_R_Y_RT_IDX_PK_CODIGO" ON "ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS" USING btree (elevadores_instalacion_codigo, resistencia_reactancia_codigo);


--
-- Name: MOTORES_EN_INSTALACION_IDX_FK_PROYECTO_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "MOTORES_EN_INSTALACION_IDX_FK_PROYECTO_CODIGO" ON "MOTORES_EN_INSTALACION" USING btree (proyecto_codigo, proyecto_tipo_instalacion);


--
-- Name: MOTORES_EN_INSTALACION_IDX_FK_TIPO_FASE_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "MOTORES_EN_INSTALACION_IDX_FK_TIPO_FASE_CODIGO" ON "MOTORES_EN_INSTALACION" USING btree (tipo_fase_codigo);


--
-- Name: PROYECTO_IDX_FK_TIPO_DE_INSTALACION; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "PROYECTO_IDX_FK_TIPO_DE_INSTALACION" ON "PROYECTO" USING btree (tipo_de_instalacion_codigo);


--
-- Name: PROYECTO_IDX_FK_USUARIO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "PROYECTO_IDX_FK_USUARIO" ON "PROYECTO" USING btree (usuario_nacionalidad, usuario_cedula);


--
-- Name: PROYECTO_IDX_NOMBRE; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "PROYECTO_IDX_NOMBRE" ON "PROYECTO" USING btree (nombre);


--
-- Name: PROYECTO_IDX_PK_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "PROYECTO_IDX_PK_CODIGO" ON "PROYECTO" USING btree (codigo, tipo_de_instalacion_codigo);


--
-- Name: USUARIO_IDX_CODIGO; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "USUARIO_IDX_CODIGO" ON "USUARIO" USING btree (nacionalidad, cedula);


--
-- Name: USUARIO_IDX_NOMBRE; Type: INDEX; Schema: negocio; Owner: postgres; Tablespace: 
--

CREATE INDEX "USUARIO_IDX_NOMBRE" ON "USUARIO" USING btree (nombre);


SET search_path = maestros, pg_catalog;

--
-- Name: CABALLOS_DE_POTENCIA_FK_CABALLO_POTENCIA; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CABALLOS_DE_POTENCIAS"
    ADD CONSTRAINT "CABALLOS_DE_POTENCIA_FK_CABALLO_POTENCIA" FOREIGN KEY (caballo_de_potencia_codigo) REFERENCES "CABALLO_DE_POTENCIA"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CABALLOS_DE_POTENCIA_FK_INTENSIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CABALLOS_DE_POTENCIAS"
    ADD CONSTRAINT "CABALLOS_DE_POTENCIA_FK_INTENSIDAD" FOREIGN KEY (intensidad_codigo) REFERENCES "INTENSIDAD"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CABALLOS_DE_POTENCIA_FK_VOLTAJE; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CABALLOS_DE_POTENCIAS"
    ADD CONSTRAINT "CABALLOS_DE_POTENCIA_FK_VOLTAJE" FOREIGN KEY (voltaje_codigo) REFERENCES "VOLTAJE"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CABALLO_DE_FUERZA_TRIFASICO_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CABALLO_DE_FUERZA_TRIFASICO"
    ADD CONSTRAINT "CABALLO_DE_FUERZA_TRIFASICO_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo);


--
-- Name: CABALLO_DE_POTENCIA_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CABALLO_DE_POTENCIA"
    ADD CONSTRAINT "CABALLO_DE_POTENCIA_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CALIBRES_FK_CALIBRE; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CALIBRES"
    ADD CONSTRAINT "CALIBRES_FK_CALIBRE" FOREIGN KEY (calibre_codigo) REFERENCES "CALIBRE"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: CALIBRES_FK_INTENSIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CALIBRES"
    ADD CONSTRAINT "CALIBRES_FK_INTENSIDAD" FOREIGN KEY (intensidad_codigo) REFERENCES "INTENSIDAD"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: CALIBRES_FK_MATERIAL; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CALIBRES"
    ADD CONSTRAINT "CALIBRES_FK_MATERIAL" FOREIGN KEY (material_codigo) REFERENCES "MATERIAL"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: CALIBRES_FK_TEMPERATURA; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CALIBRES"
    ADD CONSTRAINT "CALIBRES_FK_TEMPERATURA" FOREIGN KEY (temperatura_codigo) REFERENCES "TEMPERATURA"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: CARGA_FK_ENERGIA; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CARGA"
    ADD CONSTRAINT "CARGA_FK_ENERGIA" FOREIGN KEY (energia_codigo) REFERENCES "ENERGIA"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CARGA_FK_POTENCIA; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CARGA"
    ADD CONSTRAINT "CARGA_FK_POTENCIA" FOREIGN KEY (potencia_codigo) REFERENCES "POTENCIA"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CARGA_FK_TIPO_CARGA_CODIGO; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CARGA"
    ADD CONSTRAINT "CARGA_FK_TIPO_CARGA_CODIGO" FOREIGN KEY (tipo_carga_codigo) REFERENCES "TIPO_CARGA"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CONDUCTORES_DE_TIERRA_FK_CALIBRE; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CONDUCTORES_DE_TIERRA"
    ADD CONSTRAINT "CONDUCTORES_DE_TIERRA_FK_CALIBRE" FOREIGN KEY (calibre_codigo) REFERENCES "CALIBRE"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CONDUCTORES_DE_TIERRA_FK_INTENSIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CONDUCTORES_DE_TIERRA"
    ADD CONSTRAINT "CONDUCTORES_DE_TIERRA_FK_INTENSIDAD" FOREIGN KEY (intensidad_codigo) REFERENCES "INTENSIDAD"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CONDUCTORES_DE_TIERRA_FK_MATERIAL; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "CONDUCTORES_DE_TIERRA"
    ADD CONSTRAINT "CONDUCTORES_DE_TIERRA_FK_MATERIAL" FOREIGN KEY (material_codigo) REFERENCES "MATERIAL"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: ELEVADOR_FK_NUMERO_DE_PERSONAS; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "ELEVADOR"
    ADD CONSTRAINT "ELEVADOR_FK_NUMERO_DE_PERSONAS" FOREIGN KEY (numero_de_personas_codigo) REFERENCES "NUMERO_DE_PERSONAS"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: ELEVADOR_FK_POTENCIA; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "ELEVADOR"
    ADD CONSTRAINT "ELEVADOR_FK_POTENCIA" FOREIGN KEY (potencia_codigo) REFERENCES "POTENCIA"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: ELEVADOR_FK_VELOCIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "ELEVADOR"
    ADD CONSTRAINT "ELEVADOR_FK_VELOCIDAD" FOREIGN KEY (velocidad_codigo) REFERENCES "VELOCIDAD"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: ENERGIA_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "ENERGIA"
    ADD CONSTRAINT "ENERGIA_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: FASE_PORCENTAJE_MOTORES_FK_FASE; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "FASE_PORCENTAJE_MOTORES"
    ADD CONSTRAINT "FASE_PORCENTAJE_MOTORES_FK_FASE" FOREIGN KEY (fase_codigo) REFERENCES "FASE"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: FASE_PORCENTAJE_MOTORES_FK_PORCENTAJE_MOTORES; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "FASE_PORCENTAJE_MOTORES"
    ADD CONSTRAINT "FASE_PORCENTAJE_MOTORES_FK_PORCENTAJE_MOTORES" FOREIGN KEY (porcentaje_motores_codigo) REFERENCES "PORCENTAJE_MOTORES_MONOFASICOS"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: INTENSIDAD_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "INTENSIDAD"
    ADD CONSTRAINT "INTENSIDAD_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: INTERRUPTOR_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "INTERRUPTOR"
    ADD CONSTRAINT "INTERRUPTOR_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: MARCA_DE_CABLE_FK_CABLE; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "MARCA_DE_CABLE"
    ADD CONSTRAINT "MARCA_DE_CABLE_FK_CABLE" FOREIGN KEY (cable_codigo) REFERENCES "CABLE"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: MARCA_DE_CABLE_FK_MATERIAL; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "MARCA_DE_CABLE"
    ADD CONSTRAINT "MARCA_DE_CABLE_FK_MATERIAL" FOREIGN KEY (material_codigo) REFERENCES "MATERIAL"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: MARCA_DE_CABLE_FK_TEMPERATURA; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "MARCA_DE_CABLE"
    ADD CONSTRAINT "MARCA_DE_CABLE_FK_TEMPERATURA" FOREIGN KEY (temperatura_codigo) REFERENCES "TEMPERATURA"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: MOTORES_TRIFASICOS_DE_INDUCCION_FK_HP; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "MOTORES_TRIFASICOS_DE_INDUCCION"
    ADD CONSTRAINT "MOTORES_TRIFASICOS_DE_INDUCCION_FK_HP" FOREIGN KEY (caballo_de_fuerza_trifasico_codigo) REFERENCES "CABALLO_DE_FUERZA_TRIFASICO"(codigo);


--
-- Name: MOTORES_TRIFASICOS_DE_INDUCCION_FK_VOLTAJE; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "MOTORES_TRIFASICOS_DE_INDUCCION"
    ADD CONSTRAINT "MOTORES_TRIFASICOS_DE_INDUCCION_FK_VOLTAJE" FOREIGN KEY (voltaje_motores_trifasicos_codigo) REFERENCES "VOLTAJE_MOTORES_TRIFASICOS"(codigo);


--
-- Name: PORCENTAJE_MOTORES_MONOFASICOS_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "PORCENTAJE_MOTORES_MONOFASICOS"
    ADD CONSTRAINT "PORCENTAJE_MOTORES_MONOFASICOS_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: PORCENTAJE_MOTORES_TRIFASICOS_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "PORCENTAJE_MOTORES_TRIFASICOS"
    ADD CONSTRAINT "PORCENTAJE_MOTORES_TRIFASICOS_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: POTENCIA_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "POTENCIA"
    ADD CONSTRAINT "POTENCIA_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: RESISTENCIA_REACTANCIA_FK_CALIBRE; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "RESISTENCIA_REACTANCIA"
    ADD CONSTRAINT "RESISTENCIA_REACTANCIA_FK_CALIBRE" FOREIGN KEY (calibre_codigo) REFERENCES "CALIBRE"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: RESISTENCIA_REACTANCIA_FK_DUCTO; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "RESISTENCIA_REACTANCIA"
    ADD CONSTRAINT "RESISTENCIA_REACTANCIA_FK_DUCTO" FOREIGN KEY (ducto_codigo) REFERENCES "DUCTO"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: RESISTENCIA_REACTANCIA_FK_MATERIAL; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "RESISTENCIA_REACTANCIA"
    ADD CONSTRAINT "RESISTENCIA_REACTANCIA_FK_MATERIAL" FOREIGN KEY (material_codigo) REFERENCES "MATERIAL"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: RESISTENCIA_REACTANCIA_FK_VALOR; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "RESISTENCIA_REACTANCIA"
    ADD CONSTRAINT "RESISTENCIA_REACTANCIA_FK_VALOR" FOREIGN KEY (valor_codigo) REFERENCES "VALOR"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: TEMPERATURA_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "TEMPERATURA"
    ADD CONSTRAINT "TEMPERATURA_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: VELOCIDAD_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "VELOCIDAD"
    ADD CONSTRAINT "VELOCIDAD_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: VOLTAJE_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "VOLTAJE"
    ADD CONSTRAINT "VOLTAJE_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: VOLTAJE_MOTORES_TRIFASICOS_FK_UNIDAD; Type: FK CONSTRAINT; Schema: maestros; Owner: postgres
--

ALTER TABLE ONLY "VOLTAJE_MOTORES_TRIFASICOS"
    ADD CONSTRAINT "VOLTAJE_MOTORES_TRIFASICOS_FK_UNIDAD" FOREIGN KEY (unidad_codigo) REFERENCES "UNIDAD"(codigo);


SET search_path = negocio, pg_catalog;

--
-- Name: ALIMENTADOR_FK_PROYECTO_CODIGO; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "ALIMENTADOR_PRINCIPAL"
    ADD CONSTRAINT "ALIMENTADOR_FK_PROYECTO_CODIGO" FOREIGN KEY (proyecto_codigo, proyecto_tipo) REFERENCES "PROYECTO"(codigo, tipo_de_instalacion_codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ALIMENTADOR_FK_TIPO_CARGA_CODIGO; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "ALIMENTADOR_PRINCIPAL"
    ADD CONSTRAINT "ALIMENTADOR_FK_TIPO_CARGA_CODIGO" FOREIGN KEY (tipo_carga_codigo) REFERENCES maestros."TIPO_CARGA"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: AREA_FK_PROYECTO; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA"
    ADD CONSTRAINT "AREA_FK_PROYECTO" FOREIGN KEY (proyecto_codigo, proyecto_tipo_instalacion) REFERENCES "PROYECTO"(codigo, tipo_de_instalacion_codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: AREA_I_O_T_FK_AREA; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES"
    ADD CONSTRAINT "AREA_I_O_T_FK_AREA" FOREIGN KEY (area_codigo) REFERENCES "AREA"(codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: AREA_I_O_T_FK_CALIBRE; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES"
    ADD CONSTRAINT "AREA_I_O_T_FK_CALIBRE" FOREIGN KEY (calibre_codigo) REFERENCES maestros."CALIBRE"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: AREA_I_O_T_FK_CALIBRES; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES"
    ADD CONSTRAINT "AREA_I_O_T_FK_CALIBRES" FOREIGN KEY (calibres_codigo) REFERENCES maestros."CALIBRES"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: AREA_I_O_T_FK_DUCTO; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES"
    ADD CONSTRAINT "AREA_I_O_T_FK_DUCTO" FOREIGN KEY (ducto_codigo) REFERENCES maestros."DUCTO"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: AREA_I_O_T_FK_FASE; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES"
    ADD CONSTRAINT "AREA_I_O_T_FK_FASE" FOREIGN KEY (fase_codigo) REFERENCES maestros."FASE"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: AREA_I_O_T_FK_INTERRUPTOR; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES"
    ADD CONSTRAINT "AREA_I_O_T_FK_INTERRUPTOR" FOREIGN KEY (interruptor_codigo) REFERENCES maestros."INTERRUPTOR"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: AREA_I_O_T_FK_VOLTAJE; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES"
    ADD CONSTRAINT "AREA_I_O_T_FK_VOLTAJE" FOREIGN KEY (voltaje_codigo) REFERENCES maestros."VOLTAJE"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_FK_A_I_T; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA"
    ADD CONSTRAINT "AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_FK_A_I_T" FOREIGN KEY (area_iluminaria_toma_corriente_codigo) REFERENCES "AREA_ILUMINARIA_TOMA_CORRIENTES"(codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_FK_A_R_X; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "AREA_ILUMINARIA_TOMA_CORRIENTES_RESISTENCIA_REACTANCIA"
    ADD CONSTRAINT "AREA_I_T_CORRIENTES_RESISTENCIA_REACTANCIA_FK_A_R_X" FOREIGN KEY (resistencia_reactancia_codigo) REFERENCES maestros."RESISTENCIA_REACTANCIA"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CARGAS_EN_AREAS_FK_AREA; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "CARGAS_EN_AREAS"
    ADD CONSTRAINT "CARGAS_EN_AREAS_FK_AREA" FOREIGN KEY (codigo_area) REFERENCES "AREA"(codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: CARGAS_EN_AREAS_FK_CARGA; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "CARGAS_EN_AREAS"
    ADD CONSTRAINT "CARGAS_EN_AREAS_FK_CARGA" FOREIGN KEY (codigo_carga) REFERENCES maestros."CARGA"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CARGAS_EN_AREAS_FK_FASE; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "CARGAS_EN_AREAS"
    ADD CONSTRAINT "CARGAS_EN_AREAS_FK_FASE" FOREIGN KEY (fase_codigo) REFERENCES maestros."FASE"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: CIRCUITO_DE_ILUMINACION_FK_PTOYECTO_CODIGO; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "CIRCUITO_DE_ILUMINACION"
    ADD CONSTRAINT "CIRCUITO_DE_ILUMINACION_FK_PTOYECTO_CODIGO" FOREIGN KEY (proyecto_codigo, proyecto_tipo_instalacion) REFERENCES "PROYECTO"(codigo, tipo_de_instalacion_codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ELEVADORES_INSTALACION_FK_CALIBRES; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "ELEVADORES_EN_LA_INSTALACION"
    ADD CONSTRAINT "ELEVADORES_INSTALACION_FK_CALIBRES" FOREIGN KEY (calibres_codigo) REFERENCES maestros."CALIBRES"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: ELEVADORES_INSTALACION_FK_ELEVADOR; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "ELEVADORES_EN_LA_INSTALACION"
    ADD CONSTRAINT "ELEVADORES_INSTALACION_FK_ELEVADOR" FOREIGN KEY (elevador_codigo) REFERENCES maestros."ELEVADOR"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: ELEVADORES_INSTALACION_FK_PROYECTO; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "ELEVADORES_EN_LA_INSTALACION"
    ADD CONSTRAINT "ELEVADORES_INSTALACION_FK_PROYECTO" FOREIGN KEY (proyecto_codigo, proyecto_tipo_instalacion) REFERENCES "PROYECTO"(codigo, tipo_de_instalacion_codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ELEVADORES_INSTALACION_R_Y_RT_FK_ELEVADORES_INSTALACION; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS"
    ADD CONSTRAINT "ELEVADORES_INSTALACION_R_Y_RT_FK_ELEVADORES_INSTALACION" FOREIGN KEY (elevadores_instalacion_codigo) REFERENCES "ELEVADORES_EN_LA_INSTALACION"(codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ELEVADORES_INSTALACION_R_Y_RT_FK_RESISTENCIA_REACTANCIA; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "ELEVADORES_INSTALACION_RESISTENCIAS_Y_REACTANCIAS"
    ADD CONSTRAINT "ELEVADORES_INSTALACION_R_Y_RT_FK_RESISTENCIA_REACTANCIA" FOREIGN KEY (resistencia_reactancia_codigo) REFERENCES maestros."RESISTENCIA_REACTANCIA"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: MOTORES_EN_INSTALACION_FK_PROYECTO_CODIGO; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "MOTORES_EN_INSTALACION"
    ADD CONSTRAINT "MOTORES_EN_INSTALACION_FK_PROYECTO_CODIGO" FOREIGN KEY (proyecto_codigo, proyecto_tipo_instalacion) REFERENCES "PROYECTO"(codigo, tipo_de_instalacion_codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: MOTORES_EN_INSTALACION_FK_TIPO_FASE_CODIGO; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "MOTORES_EN_INSTALACION"
    ADD CONSTRAINT "MOTORES_EN_INSTALACION_FK_TIPO_FASE_CODIGO" FOREIGN KEY (tipo_fase_codigo) REFERENCES maestros."TIPO_FASE"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: PROYECTO_FK; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "CONDUCTORES_TUBERIAS_ALIMENTADOR_PRINCIPAL"
    ADD CONSTRAINT "PROYECTO_FK" FOREIGN KEY (proyecto_codigo, proyecto_tipo) REFERENCES "PROYECTO"(codigo, tipo_de_instalacion_codigo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: PROYECTO_FK_TIPO_DE_INSTALACION; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "PROYECTO"
    ADD CONSTRAINT "PROYECTO_FK_TIPO_DE_INSTALACION" FOREIGN KEY (tipo_de_instalacion_codigo) REFERENCES maestros."TIPO_DE_INSTALACION"(codigo) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: PROYECTO_FK_USUARIO; Type: FK CONSTRAINT; Schema: negocio; Owner: postgres
--

ALTER TABLE ONLY "PROYECTO"
    ADD CONSTRAINT "PROYECTO_FK_USUARIO" FOREIGN KEY (usuario_nacionalidad, usuario_cedula) REFERENCES "USUARIO"(nacionalidad, cedula) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: negocio; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA negocio FROM PUBLIC;
REVOKE ALL ON SCHEMA negocio FROM postgres;
GRANT ALL ON SCHEMA negocio TO postgres;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

