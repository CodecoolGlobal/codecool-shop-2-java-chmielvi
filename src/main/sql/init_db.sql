DROP TABLE IF EXISTS public.product_categories;
DROP TABLE IF EXISTS public.products_categories;
CREATE TABLE public.product_categories (
                                   id serial NOT NULL PRIMARY KEY,
                                   name text NOT NULL,
                                   description text NOT NULL,
                                   department text NOT NULL
);


DROP TABLE IF EXISTS public.products;
CREATE TABLE public.products (
                                 id serial NOT NULL PRIMARY KEY,
                                 name text NOT NULL,
                                 description text NOT NULL,
                                 default_currency text NOT NULL ,
                                 product_category_id integer,
                                 supplier_id integer

);

DROP TABLE IF EXISTS public.supplier;
CREATE TABLE public.supplier (
                                 id serial NOT NULL PRIMARY KEY,
                                 name text NOT NULL,
                                 description text NOT NULL

);

