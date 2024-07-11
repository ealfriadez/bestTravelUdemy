PGDMP  3                    |            qbo    16.3 (Debian 16.3-1.pgdg120+1)    16.2 S    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16389    qbo    DATABASE     n   CREATE DATABASE qbo WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF8';
    DROP DATABASE qbo;
                qbo_user    false            �           0    0    qbo    DATABASE PROPERTIES     ,   ALTER DATABASE qbo SET "TimeZone" TO 'utc';
                     qbo_user    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                qbo_user    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   qbo_user    false    5            �            1259    18680    cities    TABLE     �   CREATE TABLE public.cities (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    country_id bigint NOT NULL
);
    DROP TABLE public.cities;
       public         heap    qbo_user    false    5            �            1259    18679    cities_id_seq    SEQUENCE     v   CREATE SEQUENCE public.cities_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.cities_id_seq;
       public          qbo_user    false    5    231            �           0    0    cities_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.cities_id_seq OWNED BY public.cities.id;
          public          qbo_user    false    230            �            1259    18687 	   countries    TABLE     d   CREATE TABLE public.countries (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.countries;
       public         heap    qbo_user    false    5            �            1259    18686    countries_id_seq    SEQUENCE     y   CREATE SEQUENCE public.countries_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.countries_id_seq;
       public          qbo_user    false    233    5            �           0    0    countries_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.countries_id_seq OWNED BY public.countries.id;
          public          qbo_user    false    232            �            1259    16399    customer    TABLE     C  CREATE TABLE public.customer (
    dni character varying(20) NOT NULL,
    full_name character varying(50) NOT NULL,
    credit_card character varying(20) NOT NULL,
    total_flights integer NOT NULL,
    total_lodgings integer NOT NULL,
    total_tours integer NOT NULL,
    phone_number character varying(20) NOT NULL
);
    DROP TABLE public.customer;
       public         heap    qbo_user    false    5            �            1259    16405    fly    TABLE     n  CREATE TABLE public.fly (
    id bigint NOT NULL,
    origin_lat numeric NOT NULL,
    origin_lng numeric NOT NULL,
    destiny_lng numeric NOT NULL,
    destiny_lat numeric NOT NULL,
    origin_name character varying(20) NOT NULL,
    destiny_name character varying(20) NOT NULL,
    aero_line character varying(20) NOT NULL,
    price double precision NOT NULL
);
    DROP TABLE public.fly;
       public         heap    qbo_user    false    5            �            1259    16404 
   fly_id_seq    SEQUENCE     s   CREATE SEQUENCE public.fly_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.fly_id_seq;
       public          qbo_user    false    5    217            �           0    0 
   fly_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.fly_id_seq OWNED BY public.fly.id;
          public          qbo_user    false    216            �            1259    16414    hotel    TABLE     �   CREATE TABLE public.hotel (
    id bigint NOT NULL,
    name character varying(50) NOT NULL,
    address character varying(50) NOT NULL,
    rating integer NOT NULL,
    price double precision NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    qbo_user    false    5            �            1259    16413    hotel_id_seq    SEQUENCE     u   CREATE SEQUENCE public.hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.hotel_id_seq;
       public          qbo_user    false    219    5            �           0    0    hotel_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.hotel_id_seq OWNED BY public.hotel.id;
          public          qbo_user    false    218            �            1259    16432    reservation    TABLE     E  CREATE TABLE public.reservation (
    id uuid NOT NULL,
    date_reservation timestamp without time zone NOT NULL,
    date_start date NOT NULL,
    date_end date,
    total_days integer NOT NULL,
    price double precision NOT NULL,
    tour_id bigint,
    hotel_id bigint,
    customer_id character varying(20) NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    qbo_user    false    5            �            1259    18638    tbl_comments    TABLE     �   CREATE TABLE public.tbl_comments (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    post_id bigint,
    user_id bigint,
    content text
);
     DROP TABLE public.tbl_comments;
       public         heap    qbo_user    false    5            �            1259    18637    tbl_comments_id_seq    SEQUENCE     |   CREATE SEQUENCE public.tbl_comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.tbl_comments_id_seq;
       public          qbo_user    false    225    5            �           0    0    tbl_comments_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.tbl_comments_id_seq OWNED BY public.tbl_comments.id;
          public          qbo_user    false    224            �            1259    18647 	   tbl_posts    TABLE     �   CREATE TABLE public.tbl_posts (
    created_at timestamp(6) without time zone,
    id bigint NOT NULL,
    user_id bigint,
    content text,
    title character varying(255)
);
    DROP TABLE public.tbl_posts;
       public         heap    qbo_user    false    5            �            1259    18646    tbl_posts_id_seq    SEQUENCE     y   CREATE SEQUENCE public.tbl_posts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.tbl_posts_id_seq;
       public          qbo_user    false    5    227            �           0    0    tbl_posts_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.tbl_posts_id_seq OWNED BY public.tbl_posts.id;
          public          qbo_user    false    226            �            1259    18656 	   tbl_users    TABLE     �   CREATE TABLE public.tbl_users (
    id bigint NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    username character varying(255)
);
    DROP TABLE public.tbl_users;
       public         heap    qbo_user    false    5            �            1259    18655    tbl_users_id_seq    SEQUENCE     y   CREATE SEQUENCE public.tbl_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.tbl_users_id_seq;
       public          qbo_user    false    5    229            �           0    0    tbl_users_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.tbl_users_id_seq OWNED BY public.tbl_users.id;
          public          qbo_user    false    228            �            1259    16452    ticket    TABLE     b  CREATE TABLE public.ticket (
    id uuid NOT NULL,
    price double precision NOT NULL,
    fly_id bigint NOT NULL,
    customer_id character varying(20) NOT NULL,
    departure_date timestamp without time zone NOT NULL,
    arrival_date timestamp without time zone NOT NULL,
    purchase_date timestamp without time zone NOT NULL,
    tour_id bigint
);
    DROP TABLE public.ticket;
       public         heap    qbo_user    false    5            �            1259    16421    tour    TABLE     e   CREATE TABLE public.tour (
    id bigint NOT NULL,
    id_customer character varying(20) NOT NULL
);
    DROP TABLE public.tour;
       public         heap    qbo_user    false    5            �            1259    16420    tour_id_seq    SEQUENCE     t   CREATE SEQUENCE public.tour_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.tour_id_seq;
       public          qbo_user    false    221    5            �           0    0    tour_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.tour_id_seq OWNED BY public.tour.id;
          public          qbo_user    false    220            �           2604    18683 	   cities id    DEFAULT     f   ALTER TABLE ONLY public.cities ALTER COLUMN id SET DEFAULT nextval('public.cities_id_seq'::regclass);
 8   ALTER TABLE public.cities ALTER COLUMN id DROP DEFAULT;
       public          qbo_user    false    230    231    231            �           2604    18690    countries id    DEFAULT     l   ALTER TABLE ONLY public.countries ALTER COLUMN id SET DEFAULT nextval('public.countries_id_seq'::regclass);
 ;   ALTER TABLE public.countries ALTER COLUMN id DROP DEFAULT;
       public          qbo_user    false    232    233    233            �           2604    16408    fly id    DEFAULT     `   ALTER TABLE ONLY public.fly ALTER COLUMN id SET DEFAULT nextval('public.fly_id_seq'::regclass);
 5   ALTER TABLE public.fly ALTER COLUMN id DROP DEFAULT;
       public          qbo_user    false    216    217    217            �           2604    16417    hotel id    DEFAULT     d   ALTER TABLE ONLY public.hotel ALTER COLUMN id SET DEFAULT nextval('public.hotel_id_seq'::regclass);
 7   ALTER TABLE public.hotel ALTER COLUMN id DROP DEFAULT;
       public          qbo_user    false    218    219    219            �           2604    18641    tbl_comments id    DEFAULT     r   ALTER TABLE ONLY public.tbl_comments ALTER COLUMN id SET DEFAULT nextval('public.tbl_comments_id_seq'::regclass);
 >   ALTER TABLE public.tbl_comments ALTER COLUMN id DROP DEFAULT;
       public          qbo_user    false    224    225    225            �           2604    18650    tbl_posts id    DEFAULT     l   ALTER TABLE ONLY public.tbl_posts ALTER COLUMN id SET DEFAULT nextval('public.tbl_posts_id_seq'::regclass);
 ;   ALTER TABLE public.tbl_posts ALTER COLUMN id DROP DEFAULT;
       public          qbo_user    false    226    227    227            �           2604    18659    tbl_users id    DEFAULT     l   ALTER TABLE ONLY public.tbl_users ALTER COLUMN id SET DEFAULT nextval('public.tbl_users_id_seq'::regclass);
 ;   ALTER TABLE public.tbl_users ALTER COLUMN id DROP DEFAULT;
       public          qbo_user    false    228    229    229            �           2604    16424    tour id    DEFAULT     b   ALTER TABLE ONLY public.tour ALTER COLUMN id SET DEFAULT nextval('public.tour_id_seq'::regclass);
 6   ALTER TABLE public.tour ALTER COLUMN id DROP DEFAULT;
       public          qbo_user    false    220    221    221            ~          0    18680    cities 
   TABLE DATA           6   COPY public.cities (id, name, country_id) FROM stdin;
    public          qbo_user    false    231   e]       �          0    18687 	   countries 
   TABLE DATA           -   COPY public.countries (id, name) FROM stdin;
    public          qbo_user    false    233   �]       n          0    16399    customer 
   TABLE DATA           y   COPY public.customer (dni, full_name, credit_card, total_flights, total_lodgings, total_tours, phone_number) FROM stdin;
    public          qbo_user    false    215   ^       p          0    16405    fly 
   TABLE DATA           �   COPY public.fly (id, origin_lat, origin_lng, destiny_lng, destiny_lat, origin_name, destiny_name, aero_line, price) FROM stdin;
    public          qbo_user    false    217   �^       r          0    16414    hotel 
   TABLE DATA           A   COPY public.hotel (id, name, address, rating, price) FROM stdin;
    public          qbo_user    false    219   �_       u          0    16432    reservation 
   TABLE DATA           �   COPY public.reservation (id, date_reservation, date_start, date_end, total_days, price, tour_id, hotel_id, customer_id) FROM stdin;
    public          qbo_user    false    222   Ta       x          0    18638    tbl_comments 
   TABLE DATA           Q   COPY public.tbl_comments (created_at, id, post_id, user_id, content) FROM stdin;
    public          qbo_user    false    225   @f       z          0    18647 	   tbl_posts 
   TABLE DATA           L   COPY public.tbl_posts (created_at, id, user_id, content, title) FROM stdin;
    public          qbo_user    false    227   �f       |          0    18656 	   tbl_users 
   TABLE DATA           H   COPY public.tbl_users (id, email, name, password, username) FROM stdin;
    public          qbo_user    false    229   �g       v          0    16452    ticket 
   TABLE DATA           v   COPY public.ticket (id, price, fly_id, customer_id, departure_date, arrival_date, purchase_date, tour_id) FROM stdin;
    public          qbo_user    false    223   6h       t          0    16421    tour 
   TABLE DATA           /   COPY public.tour (id, id_customer) FROM stdin;
    public          qbo_user    false    221   �k       �           0    0    cities_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.cities_id_seq', 9, true);
          public          qbo_user    false    230            �           0    0    countries_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.countries_id_seq', 6, true);
          public          qbo_user    false    232            �           0    0 
   fly_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.fly_id_seq', 15, true);
          public          qbo_user    false    216            �           0    0    hotel_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.hotel_id_seq', 18, true);
          public          qbo_user    false    218            �           0    0    tbl_comments_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.tbl_comments_id_seq', 8, true);
          public          qbo_user    false    224            �           0    0    tbl_posts_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.tbl_posts_id_seq', 5, true);
          public          qbo_user    false    226            �           0    0    tbl_users_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.tbl_users_id_seq', 4, true);
          public          qbo_user    false    228            �           0    0    tour_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.tour_id_seq', 20, true);
          public          qbo_user    false    220            �           2606    18685    cities cities_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.cities
    ADD CONSTRAINT cities_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.cities DROP CONSTRAINT cities_pkey;
       public            qbo_user    false    231            �           2606    18692    countries countries_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.countries
    ADD CONSTRAINT countries_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.countries DROP CONSTRAINT countries_pkey;
       public            qbo_user    false    233            �           2606    16403    customer pk_customer 
   CONSTRAINT     S   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT pk_customer PRIMARY KEY (dni);
 >   ALTER TABLE ONLY public.customer DROP CONSTRAINT pk_customer;
       public            qbo_user    false    215            �           2606    16412 
   fly pk_fly 
   CONSTRAINT     H   ALTER TABLE ONLY public.fly
    ADD CONSTRAINT pk_fly PRIMARY KEY (id);
 4   ALTER TABLE ONLY public.fly DROP CONSTRAINT pk_fly;
       public            qbo_user    false    217            �           2606    16419    hotel pk_hotel 
   CONSTRAINT     L   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT pk_hotel PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.hotel DROP CONSTRAINT pk_hotel;
       public            qbo_user    false    219            �           2606    16436    reservation pk_reservation 
   CONSTRAINT     X   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT pk_reservation PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.reservation DROP CONSTRAINT pk_reservation;
       public            qbo_user    false    222            �           2606    16456    ticket pk_ticket 
   CONSTRAINT     N   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT pk_ticket PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.ticket DROP CONSTRAINT pk_ticket;
       public            qbo_user    false    223            �           2606    16426    tour pk_tour 
   CONSTRAINT     J   ALTER TABLE ONLY public.tour
    ADD CONSTRAINT pk_tour PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.tour DROP CONSTRAINT pk_tour;
       public            qbo_user    false    221            �           2606    18645    tbl_comments tbl_comments_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.tbl_comments
    ADD CONSTRAINT tbl_comments_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.tbl_comments DROP CONSTRAINT tbl_comments_pkey;
       public            qbo_user    false    225            �           2606    18654    tbl_posts tbl_posts_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.tbl_posts
    ADD CONSTRAINT tbl_posts_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.tbl_posts DROP CONSTRAINT tbl_posts_pkey;
       public            qbo_user    false    227            �           2606    18663    tbl_users tbl_users_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.tbl_users
    ADD CONSTRAINT tbl_users_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.tbl_users DROP CONSTRAINT tbl_users_pkey;
       public            qbo_user    false    229            �           2606    18693 "   cities fk6gatmv9dwedve82icy8wrkdmk    FK CONSTRAINT     �   ALTER TABLE ONLY public.cities
    ADD CONSTRAINT fk6gatmv9dwedve82icy8wrkdmk FOREIGN KEY (country_id) REFERENCES public.countries(id);
 L   ALTER TABLE ONLY public.cities DROP CONSTRAINT fk6gatmv9dwedve82icy8wrkdmk;
       public          qbo_user    false    3283    231    233            �           2606    18664 (   tbl_comments fk99ulkbwy953fpnxxijiubpdq6    FK CONSTRAINT     �   ALTER TABLE ONLY public.tbl_comments
    ADD CONSTRAINT fk99ulkbwy953fpnxxijiubpdq6 FOREIGN KEY (post_id) REFERENCES public.tbl_posts(id);
 R   ALTER TABLE ONLY public.tbl_comments DROP CONSTRAINT fk99ulkbwy953fpnxxijiubpdq6;
       public          qbo_user    false    3277    227    225            �           2606    18669 (   tbl_comments fk9ofd356hp1c3ige9vhsciqx9m    FK CONSTRAINT     �   ALTER TABLE ONLY public.tbl_comments
    ADD CONSTRAINT fk9ofd356hp1c3ige9vhsciqx9m FOREIGN KEY (user_id) REFERENCES public.tbl_users(id);
 R   ALTER TABLE ONLY public.tbl_comments DROP CONSTRAINT fk9ofd356hp1c3ige9vhsciqx9m;
       public          qbo_user    false    3279    229    225            �           2606    16427    tour fk_customer    FK CONSTRAINT     w   ALTER TABLE ONLY public.tour
    ADD CONSTRAINT fk_customer FOREIGN KEY (id_customer) REFERENCES public.customer(dni);
 :   ALTER TABLE ONLY public.tour DROP CONSTRAINT fk_customer;
       public          qbo_user    false    3263    215    221            �           2606    16437    reservation fk_customer_r    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fk_customer_r FOREIGN KEY (customer_id) REFERENCES public.customer(dni);
 C   ALTER TABLE ONLY public.reservation DROP CONSTRAINT fk_customer_r;
       public          qbo_user    false    222    215    3263            �           2606    16457    ticket fk_customer_t    FK CONSTRAINT     {   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_customer_t FOREIGN KEY (customer_id) REFERENCES public.customer(dni);
 >   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_customer_t;
       public          qbo_user    false    215    223    3263            �           2606    16462    ticket fk_fly_t    FK CONSTRAINT     k   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_fly_t FOREIGN KEY (fly_id) REFERENCES public.fly(id);
 9   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_fly_t;
       public          qbo_user    false    217    223    3265            �           2606    16442    reservation fk_hotel_r    FK CONSTRAINT     v   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fk_hotel_r FOREIGN KEY (hotel_id) REFERENCES public.hotel(id);
 @   ALTER TABLE ONLY public.reservation DROP CONSTRAINT fk_hotel_r;
       public          qbo_user    false    222    219    3267            �           2606    16447    reservation fk_tour_r    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fk_tour_r FOREIGN KEY (tour_id) REFERENCES public.tour(id) ON DELETE CASCADE;
 ?   ALTER TABLE ONLY public.reservation DROP CONSTRAINT fk_tour_r;
       public          qbo_user    false    3269    221    222            �           2606    16467    ticket fk_tour_t    FK CONSTRAINT     �   ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT fk_tour_t FOREIGN KEY (tour_id) REFERENCES public.tour(id) ON DELETE CASCADE;
 :   ALTER TABLE ONLY public.ticket DROP CONSTRAINT fk_tour_t;
       public          qbo_user    false    223    221    3269            �           2606    18674 %   tbl_posts fksynbwn6un6oocg38h1ifn9p8y    FK CONSTRAINT     �   ALTER TABLE ONLY public.tbl_posts
    ADD CONSTRAINT fksynbwn6un6oocg38h1ifn9p8y FOREIGN KEY (user_id) REFERENCES public.tbl_users(id);
 O   ALTER TABLE ONLY public.tbl_posts DROP CONSTRAINT fksynbwn6un6oocg38h1ifn9p8y;
       public          qbo_user    false    227    229    3279            ~   G   x�3����M�4�2�)*�����r�9�K��A,NǼ��� Ӕӫ4/3�2�t�LL.M� ������ ��      �   9   x�3�H-*�2�tM.ML�/�2�t����M�L�2�t���,��8�32sR�b���� ��;      n   �   x�E��j�0���S�4lY��c�BV�20c���Ќ�����'wCH����{y�C0���1uI� �)_��2�g��{�9�&�D�Q�a�h-�����26���q���!U�0���q!� (��d@ ����=�:��$�|Yǥꧏufo%�Ī�Z.$ˀ��(+B"�����)��-�|��:��tZ�O�l�L@���d~d�N`t٪�'��7�A�      p   �   x���Ak�0��ҏ�m%�y��C���x�e�������fq�F�%��g1�@!�'���Y`�,ا�c��nH�1BLC���E���f5y̰�d-Z5_�uMuVߚ��S�����S��j6DQ�:P1��>�
+�'P'0�ձ���G���_0B���7�/ ��e���r{�����~<.�/
���BN>�q�n��[ 7`��	�2�C�[O`Q!��V��V��z�q3���"^ ����      r   H  x�M��N�0D���X��N�#��
EPq�b9nk%M I%�{֩��h����X�c��~�uOC�s%�@U��0��)e:\���A�І!l<��i�~����*��m�N�7��BIf�n��mH#(0���JH!r��|���!�t��¬����m:�q���H�7���ּ����1~hx��V
�E�ȋF��u
+� )�E �އ�<�K�M���j��L*��#��ƅ�	��4����p�S�0 
D&5OC�j�\���v������Њ�v��[�D��|M�S�P�0�Ɋ.���ZG+�q�c�-u��`�gj�L�c��5��      u   �  x���Mn[I���)r�~�f�]�E&��,���̦�����촠 �`6�>�X,RIiLO�V/���P���,Ϫ+i�r�H��(�b�R��x$S�z󯗷v�>x$��K�|���Y��������H�4c�4�����r��2񘘺���f+����%9�+;nw)UB-cN���jq�X���>�\�D�l��_�����S���B�^��S����ɗZ����i.�|)t����;nnmдLZB��!����o��JP��}_��MI;,w�&-u:���f�Vgy
�F9� o�t?4�"����;BW�UU`h)�Դr��u��f;�e~X�	��
s�B���`Kjo��Kf���mf�����w�}PU��p�p�hp�Jx*-NT˖�YΏ&���$lPn�[̡��C��y�I���~���FԂQ��|���f�l�E�!�2��)�O�ǒ-UBf�}�z����J�A�E�7�n6���ږ�d�;��*7�e��AI���Gh�Yci��Pn�����R[�9��8�����6�;k[��ň�B�jA\�sF���zM��|rC���Kܘ���s�:Qo�%�g˽�l}�%��QL�~��:�d��2�a�ϸ^M�#�����;�+�>�����v��O.e����[�GR��'X*VDo%������[���9��@fU���t3H�A���_�c?~��5
?�5k���c����.!�0�l�̄�b�f���7�dNyb�Θ�&��2�ȹkޗ�	D�m�|;Hv��[J�8QH����%��Y͉�-8_s98F���:�����/��=?e�l"+���j2������ˌ�6}�$����\N�7\�a�wlG�^��g����~���{�Q�s_�\N�7`�Af[��.,W��k�T�&)��\�:�^gnS�b��1RE�GV,�c�a��,�b�����wt�fzb�n,ȫ�4L�1�{u��6R����΢�;(H�~���=c�K7oo�Lx�!�5�#�)�uNmő���3���In�NGr�m�渟���nuF
��xmL,�>%4O:N�
��fi��R�Bu>�[�+��`��<Hf�Tb���{�U����k�����ی{����ݽ v�,#�;1\�\��`+�y��.�հ��>VG]d�E_�k�h+`c+���f����Yg�J:J���ܗnP�int��xzz���q      x   �   x���;�0E�z�
o �|=&-`4)RP@PB�+�Ы��cd�<DtdI�Q��;.���M�e���:����"nH�*�:���W:�R���ܔŝ	t�v�-�	յ8��$�)YY�\���?2�����A��_�׼n�g���)����^�      z   �   x�m̽�0���<����1�e
J*�!E�L�b
�tG��9}zȓt^;-����\/��v�K����0����"�߯�_���G�Y�j;��2�G&��0�2�τ$5*HmZ7ئ�����UB]]o�{��1{K�      |   �   x�m�M�@������zp��	�"!i3~��8�5�_�I
����p�̣�ʩF�/چ]~�ie��5�]e��q���q�r�r�����K�0"���f*O��#w��0��w�i�S�s~.����1
�ɳ�?u]�m���8 | �P?      v   �  x���M�#7���)r�+�^v� �,f��l$�t�#���.t0��a~&ߣ�`�89��R�:��wD@�(oE/xyy��RJ<������]H�����M}ìy�%����rE��T�[�grK�p�1h+�����l��ߥ��h���QgM�ES݇�2�F1�L�"��Lz��8��lR>��;��L9�3���.Iv��g�.��l�ѯp�fW�ɏ����1%%�$M պ[R�;{/=&}����펛��S�'҂��v�a5�T��k��1�x�;rc��m�%�G�ڱ����7J�U���2�{u�R��v�o��+�)WG�J[�A�L�S�$Rg��O�v���V3�Dg?�HZq�L�h�rN�̚�1#��]��ٝ�7䪟bu(-0�<[�ao��0k�8*�6�������=�����KάJϴ�X��X�14*�
�2�׳�A��܀��Y`�N~��Si��W ���{	����20�O?��w#P�U">�V��;jB�����F�xv�'<C�N�
����yġCiű��{HmBX��uf&h�}�i?+,�5e�\��u癴��{�.��{�vL5���H��xNÓ�z�l�.�`�Ci��'��wi��Ǒ(C��Y��T(���V?�g@4@���7�;���5z3|�Du��q:pb��{s4o�����Ϸ�����������ˎ.��H��]�p�6j�w�SRk�5���|��$M���A�ci�i���4=�dh�cHx_Ԧs�K������&F�p�ʱ��L�,R8�y�^t�m���[+^5�^���~�i0f{֝��ڝhJl^O3�U4���d�!+�+��{�N�f�Gw�����ܮ��/�+u8      t   P   x�34�tr�u277404��u
202�24�*j�UԜ3���*��d`j�eh����P�n`i�eh�M�� �h� �"�     