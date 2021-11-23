--
-- TOC entry 2957 (class 0 OID 17005)
-- Dependencies: 196
-- Data for Name: banque; Type: TABLE DATA; Schema: public; Owner: frozar
--

INSERT INTO public.banque VALUES ('f47c0675-dbce-4777-bcd8-8b7b0c9ba14f', 'DGFIP', 4);


--
-- TOC entry 2958 (class 0 OID 17010)
-- Dependencies: 197
-- Data for Name: carte_bancaire; Type: TABLE DATA; Schema: public; Owner: frozar
--

INSERT INTO public.carte_bancaire VALUES ('71b4ad00-9f7c-4083-b05e-276a97e9faaf', '5613', '2024-11-19 15:03:14.849', '7878 1685 5438 2758', 'f47c0675-dbce-4777-bcd8-8b7b0c9ba14f', 'a76009f8-cfe5-4986-b0ae-64144c06ffe7', '4d59ec5a-3450-4123-aca4-ac924f949479');
INSERT INTO public.carte_bancaire VALUES ('1afa3991-068c-44f0-9289-8bbf1f86db7e', '5613', '2024-11-19 15:03:15.081', '3161 9960 8470 9757', 'f47c0675-dbce-4777-bcd8-8b7b0c9ba14f', '20216a0a-a4c2-49f4-b464-1b29ce15e486', '1abff729-766b-4af8-aade-5fdc38c18db6');


--
-- TOC entry 2959 (class 0 OID 17018)
-- Dependencies: 198
-- Data for Name: compte_bancaire; Type: TABLE DATA; Schema: public; Owner: frozar
--

INSERT INTO public.compte_bancaire VALUES ('CompteEpargne', '4ef3e8a5-0a16-4461-a486-f4983788e175', '99', 'DGFIP', '1234', '0000000002', 1, 3000, 0.0200000000000000004, 'f47c0675-dbce-4777-bcd8-8b7b0c9ba14f', '8eda40a3-6f35-4a30-91e6-5a15740cda88');
INSERT INTO public.compte_bancaire VALUES ('CompteEpargne', '3785a36a-05f2-4dc6-b404-9f1db054635f', '99', 'DGFIP', '5678', '0000000003', 1, 10000, 0.0200000000000000004, 'f47c0675-dbce-4777-bcd8-8b7b0c9ba14f', '15b0bc6c-ed84-4d8e-b90e-6db6729f48bf');
INSERT INTO public.compte_bancaire VALUES ('CompteCourant', 'a76009f8-cfe5-4986-b0ae-64144c06ffe7', '99', 'DGFIP', '1234', '0000000000', 3, 0, NULL, 'f47c0675-dbce-4777-bcd8-8b7b0c9ba14f', '4d59ec5a-3450-4123-aca4-ac924f949479');
INSERT INTO public.compte_bancaire VALUES ('CompteCourant', '20216a0a-a4c2-49f4-b464-1b29ce15e486', '99', 'DGFIP', '5678', '0000000001', 3, 0, NULL, 'f47c0675-dbce-4777-bcd8-8b7b0c9ba14f', '1abff729-766b-4af8-aade-5fdc38c18db6');


--
-- TOC entry 2960 (class 0 OID 17026)
-- Dependencies: 199
-- Data for Name: compte_bancaire_operations; Type: TABLE DATA; Schema: public; Owner: frozar
--

INSERT INTO public.compte_bancaire_operations VALUES ('a76009f8-cfe5-4986-b0ae-64144c06ffe7', 'c4181503-cce9-4245-89f2-c59de155920a');
INSERT INTO public.compte_bancaire_operations VALUES ('a76009f8-cfe5-4986-b0ae-64144c06ffe7', '5e22157c-fd47-4787-8ccf-45a2c0142347');
INSERT INTO public.compte_bancaire_operations VALUES ('20216a0a-a4c2-49f4-b464-1b29ce15e486', 'd1cdfac5-08dd-4ca3-ba8d-e8aebc772e87');
INSERT INTO public.compte_bancaire_operations VALUES ('20216a0a-a4c2-49f4-b464-1b29ce15e486', 'e3fe1669-b112-4c87-88d8-9a2d516c4bd7');


--
-- TOC entry 2961 (class 0 OID 17029)
-- Dependencies: 200
-- Data for Name: operation; Type: TABLE DATA; Schema: public; Owner: frozar
--

INSERT INTO public.operation VALUES ('c4181503-cce9-4245-89f2-c59de155920a', '2021-11-19 15:03:14.715', 'Dépôt chèque', 100, 1);
INSERT INTO public.operation VALUES ('d1cdfac5-08dd-4ca3-ba8d-e8aebc772e87', '2021-11-19 15:03:14.745', 'Dépôt espèces', 200, 1);
INSERT INTO public.operation VALUES ('5e22157c-fd47-4787-8ccf-45a2c0142347', '2021-11-19 15:03:14.761', 'Débit', -50, 2);
INSERT INTO public.operation VALUES ('e3fe1669-b112-4c87-88d8-9a2d516c4bd7', '2021-11-19 15:03:14.775', 'Débit', -300, 2);
INSERT INTO public.operation VALUES ('009f4a5c-135d-4248-af45-84f633855eac', '2021-11-19 15:03:14.822', 'Dépôt', 1000, 1);
INSERT INTO public.operation VALUES ('7306f917-8e3f-4419-972d-85c33ff10185', '2021-11-19 15:03:15.207', 'Retrait gabier', -314, 3);


--
-- TOC entry 2962 (class 0 OID 17034)
-- Dependencies: 201
-- Data for Name: personne; Type: TABLE DATA; Schema: public; Owner: frozar
--

INSERT INTO public.personne VALUES ('4d59ec5a-3450-4123-aca4-ac924f949479', 'Blanchard', 'Paulette');
INSERT INTO public.personne VALUES ('1abff729-766b-4af8-aade-5fdc38c18db6', 'Guibert', 'Dominique');
INSERT INTO public.personne VALUES ('8eda40a3-6f35-4a30-91e6-5a15740cda88', 'Guillou', 'Thibault');
INSERT INTO public.personne VALUES ('15b0bc6c-ed84-4d8e-b90e-6db6729f48bf', 'Labbe', 'André');

--
-- TOC entry 2816 (class 2606 OID 16466)
-- Name: banque banque_pkey; Type: CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.banque
    ADD CONSTRAINT banque_pkey PRIMARY KEY (id);


--
-- TOC entry 2820 (class 2606 OID 16474)
-- Name: carte_bancaire carte_bancaire_pkey; Type: CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.carte_bancaire
    ADD CONSTRAINT carte_bancaire_pkey PRIMARY KEY (id);


--
-- TOC entry 2822 (class 2606 OID 16482)
-- Name: compte_bancaire compte_bancaire_pkey; Type: CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.compte_bancaire
    ADD CONSTRAINT compte_bancaire_pkey PRIMARY KEY (id);


--
-- TOC entry 2826 (class 2606 OID 16490)
-- Name: operation operation_pkey; Type: CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.operation
    ADD CONSTRAINT operation_pkey PRIMARY KEY (id);


--
-- TOC entry 2828 (class 2606 OID 16498)
-- Name: personne personne_pkey; Type: CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.personne
    ADD CONSTRAINT personne_pkey PRIMARY KEY (id);


--
-- TOC entry 2818 (class 2606 OID 16500)
-- Name: banque uk_3plaldnuw3rekjd003qd1m57p; Type: CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.banque
    ADD CONSTRAINT uk_3plaldnuw3rekjd003qd1m57p UNIQUE (code_banque);


--
-- TOC entry 2824 (class 2606 OID 16502)
-- Name: compte_bancaire_operations uk_owq8j1022tayfl6o7eng4777s; Type: CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.compte_bancaire_operations
    ADD CONSTRAINT uk_owq8j1022tayfl6o7eng4777s UNIQUE (operations_id);


--
-- TOC entry 2834 (class 2606 OID 16528)
-- Name: compte_bancaire_operations fk4d3mcgqbbgsxt143dnvrl61lb; Type: FK CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.compte_bancaire_operations
    ADD CONSTRAINT fk4d3mcgqbbgsxt143dnvrl61lb FOREIGN KEY (operations_id) REFERENCES public.operation(id);


--
-- TOC entry 2833 (class 2606 OID 16523)
-- Name: compte_bancaire fk66hy7636ji95xhoa4o97rw1ts; Type: FK CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.compte_bancaire
    ADD CONSTRAINT fk66hy7636ji95xhoa4o97rw1ts FOREIGN KEY (titulaire_id) REFERENCES public.personne(id);


--
-- TOC entry 2829 (class 2606 OID 16503)
-- Name: carte_bancaire fk9fqnbax1ksjfwtuua0kq4jo87; Type: FK CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.carte_bancaire
    ADD CONSTRAINT fk9fqnbax1ksjfwtuua0kq4jo87 FOREIGN KEY (banque_id) REFERENCES public.banque(id);


--
-- TOC entry 2831 (class 2606 OID 16513)
-- Name: carte_bancaire fkbvbxghky5h6jcyv3jcn259wyf; Type: FK CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.carte_bancaire
    ADD CONSTRAINT fkbvbxghky5h6jcyv3jcn259wyf FOREIGN KEY (titulaire_id) REFERENCES public.personne(id);


--
-- TOC entry 2832 (class 2606 OID 16518)
-- Name: compte_bancaire fkbwtlktbulne1704o9686j4ss0; Type: FK CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.compte_bancaire
    ADD CONSTRAINT fkbwtlktbulne1704o9686j4ss0 FOREIGN KEY (banque_id) REFERENCES public.banque(id);


--
-- TOC entry 2835 (class 2606 OID 16533)
-- Name: compte_bancaire_operations fkg75at5e3wkv95m6mgt28dcunp; Type: FK CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.compte_bancaire_operations
    ADD CONSTRAINT fkg75at5e3wkv95m6mgt28dcunp FOREIGN KEY (compte_bancaire_id) REFERENCES public.compte_bancaire(id);


--
-- TOC entry 2830 (class 2606 OID 16508)
-- Name: carte_bancaire fkqt51kotyrwi68q72gyssfx72n; Type: FK CONSTRAINT; Schema: public; Owner: frozar
--

ALTER TABLE ONLY public.carte_bancaire
    ADD CONSTRAINT fkqt51kotyrwi68q72gyssfx72n FOREIGN KEY (compte_courant_id) REFERENCES public.compte_bancaire(id);


-- Completed on 2021-11-19 13:26:27 +04

--
-- PostgreSQL database dump complete
--
