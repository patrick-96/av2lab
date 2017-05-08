/*objetivo criar uma function que mostre os resultados dos atletas, o usuario pode escolher
a prova, a fase, e a bateria, caso não tenha acontecido a bateria ainda mostrar uma mensagem
E fazer a trigger que impeça o alteramento e exclusão de algumas tabelas*/

create database atletismo 
use atletismo

create table paises (
CodigoPais int identity,--pode ser tirado e colocado o coi como primary key
nomePais varchar (100),
CoiPais varchar(3)
primary key(CodigoPais)
)

insert into paises values
('Afeganistão', 'AFG'),
('Angola', 'ANG'),
('Argentina', 'ARG'),
('Australia', 'AUS'),
('Austria','AUT'),
('Bolivia', 'BOL'),
('Brasil', 'BRA'),
('Canada', 'CAN'),
('China','CHN'),
('Cuba', 'CUB'),
('Alemanha', 'GER'),
('Chile', 'CHI'),
('Costa do Marfim', 'CIV'),
('Colômbia', 'COL'),
('Croácia', 'CRO'),
('Egito', 'EGY'),
('Espanha', 'ESP'),
('França', 'FRA'),
('Reino Unido', 'GBR'),
('Grécia', 'GRE'),
('Guatemala', 'GUA'),
('Granada', 'GRN'),
('Haiti', 'HAI'),
('Italia', 'ITA'),
('Jamaica', 'JAM'),
('Jordania', 'JOR'),
('Liechtenstein', 'LIE'),
('Mexico', 'MEX'),
('Paises Baixos', 'NED'),
('Portugal', 'POR'),
('Coreia do Norte', 'PRK'),
('Estados Unidos', 'USA'),
('Uruguai', 'URU'),
('Zimbabwe', 'ZIM')


select * from paises





--trigger para não deixar homens se registrarem em provas femininas e vice-versa
create trigger t_genero
on competicao
for insert
as
begin
declare 
@atleta as int,
@prova as int,
@generoAtleta as varchar(10),
@generoProva as varchar(10)
select @atleta= codigoAtleta from inserted
select @prova= codigoProva from inserted
set @generoAtleta=(select atleta.sexo from atleta where atleta.codigoAtleta=@atleta)
set @generoProva=(select provas.sexoProva from provas where provas.codigoProva=@prova)
if @generoProva =@generoAtleta
begin
select *from inserted
end
else if @generoProva <> @generoAtleta
 begin
 rollback transaction
 raiserror ('Os gêneros do atleta e prova devem coincidir',16,1)
 end
 end

 drop trigger t_genero

 --triggers para não permitir alterar nem excluir tabelas com o resultado, paises e atletas
 create trigger t_youshallnotpassA
 on atleta
 for update,delete
 as
 begin
 rollback transaction
 raiserror('Não é permitido alterar nem excluir está table',10,2)
 end

 create trigger t_youshallnotpassP
 on provas
 for update,delete
 as
 begin
 rollback transaction
 raiserror('Não é permitido alterar nem excluir está table',10,2)
 end

 create trigger t_youshallnotpassC
 on competicao
 for update,delete
 as
 begin
 rollback transaction
 raiserror('Não é permitido alterar nem excluir está table',10,2)
 end










--deu bom, unico problema é o genero da prova
CREATE FUNCTION fn_tabela(@nomeProva varchar(100),@fase varchar(100),@bateria int)
RETURNS @tabela TABLE(
nomeProva VARCHAR(200),
nomeAtleta varchar(200),
fase varchar(100),
bateria int,
resultado varchar(100))
as 
begin
declare @tipoProva  char(2)
set @tipoProva=(select provas.tipoProva from provas where provas.NomeProva=@nomeProva) 
if @tipoProva like 'C'
begin
insert into @tabela(nomeProva,nomeAtleta,fase,bateria,resultado)
select provas.NomeProva,atleta.nomeAtleta,competicao.fase,competicao.bateria,cast(competicao.resultado as time) as resultado
from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where provas.NomeProva=@nomeProva and competicao.fase=@fase and competicao.bateria=@bateria
end
else if @tipoProva like 'SA'
begin
insert into @tabela(nomeProva,nomeAtleta,fase,bateria,resultado)
select provas.NomeProva,atleta.nomeAtleta,competicao.fase,competicao.bateria,competicao.resultado 
from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where provas.NomeProva=@nomeProva and competicao.fase=@fase and competicao.bateria=@bateria
end
return 
end

select * from fn_tabela('lançamento do dardo','Final',2)

select * from provas
select * from competicao
drop Function fn_tabela











create table atleta (
codigoAtleta int identity,
nomeAtleta varchar(100),
sexo varchar(10),
CodigoPais int--boa, aparentemento o atleta tem que ter um vinculo com o pais
primary key (codigoAtleta)
foreign key (codigoPais) references paises(CodigoPais)

)
drop table atleta
insert into atleta values
('Ariane','feminino',5),
('gabriela','feminino',2),
('Ana', 'feminino', 6 ),
('sofia', 'feminino',11),
('natalia', 'feminino', 4),
('Rafaela','feminino', 21),
('Abigail','feminino',4),
('Amanda', 'feminino',16),
('Heloisa', 'feminino', 12),
('Greice', 'feminino', 8),
('Sara','feminino',19),
('isabela', 'feminino', 11),
('Janaina', 'feminino', 7),
('Lia', 'feminino', 5 ),
('Daniela', 'feminino',10),
('Beatriz', 'feminino', 19),

('alex', 'masculino' , 7),
('patrick' , 'masculno',7 ),
('Aderson', 'masculino', 2 ),
('Lucas','masculino',10),
('Antonio','masculino', 7),
('valentin','masculino',8),
('clayton','masculino',20),
('fernando', 'masculino',1),
('Wendel', 'masculino',19),
('Lucas','masculino',13),
('Matheus','masculino',8),
('Jadson','masculino',23),
('Luan', 'masculino',1),
('Rafael', 'masculino', 20),
('Patrick', 'masculino', 3),
('Filipe', 'masculino', 12 ),


('Arthur', 'masculino', 9 ),
('Flavio', 'masculino', 16 ),
('Carlos', 'masculino', 22),
('Athos', 'masculino', 25),
('Douglas','masculino',17),
('Hugo', 'masculino', 20 ),
('Rossi', 'masculino',10),
('Hugo', 'masculino', 20 ),
('Caio', 'masculino', 9 ),
('Bento', 'masculino', 21 ),
('Hugo', 'masculino', 20 ),
('Davi', 'masculino', 8 ),
('Cicero', 'masculino', 12 ),
('Caetano', 'masculino', 2 ),
('Augusto', 'masculino', 4 ),
('George', 'masculino', 8 ),

('Henrique', 'masculino', 11 ),
('Enrico', 'masculino', 3 ),
('Heitor', 'masculino', 31 ),
('Inacio', 'masculino', 34 ),
('Joaquim', 'masculino', 29 ),
('Eduardo', 'masculino', 27 ),
('Conrado', 'masculino', 23),
('Julio', 'masculino', 20 ),
('Lorenzo', 'masculino', 29 ),
('Leonardo', 'masculino', 28 ),
('Max', 'masculino', 2 ),
('Murilo', 'masculino', 24),
('Olavo', 'masculino', 6 ),
('Paco', 'masculino', 11),
('Lionel', 'masculino', 31 ),
('Ramon', 'masculino', 20 ),


('Marta', 'feminino', 3),
('Maniana','feminino',10),
('Cristiane', 'feminino', 33),
('Cecilia', 'feminino', 6),
('Aurora', 'feminino', 32),
('Betina', 'feminino', 27),
('Gisela', 'feminino', 29),
('Berenice', 'feminino', 5),
('Lara', 'feminino', 32),
('Isabela', 'feminino', 3),
('Lis', 'feminino', 26),
('Manuela', 'feminino', 22),
('Livia', 'feminino', 6),
('Nice', 'feminino', 4),
('Olivia', 'feminino', 7),
('Paula', 'feminino', 28),

('Igor', 'masculino', 33 ),
('Guilherme', 'masculino', 21 ),
('Gustavo', 'masculino', 5 ),
('Vinicius', 'masculino', 32 ),
('Enzo', 'masculino', 29 ),
('Marcos Paulo', 'masculino', 2 ),
('Salvador', 'masculino', 3),
('Jesus', 'masculino', 22 ),
('Reginaldo', 'masculino', 11 ),
('Kaio', 'masculino', 17 ),
('Sergio', 'masculino', 27 ),
('Dominique', 'masculino', 18),
('Paul', 'masculino', 9 ),
('Wade', 'masculino', 1),
('Arnold', 'masculino', 7 ),
('Silvestre', 'masculino', 22 ),

('Rafael silva', 'masculino', 22 ),
('Wilton', 'masculino',4 ),
('Nicolau', 'masculino', 24 ),
('Kenneth', 'masculino', 7 ),
('Bruno', 'masculino', 18 ),
('Felix', 'masculino', 17 ),
('Silva', 'masculino', 13),
('Souza', 'masculino', 2 ),
('Batista', 'masculino', 6 ),
('Araujo', 'masculino', 32 ),
('Manoel junior', 'masculino', 29 ),
('Angelo', 'masculino', 25),
('Colevati', 'masculino', 14 ),
('Michel', 'masculino', 7),
('Andre', 'masculino', 3 ),
('Rodrigo', 'masculino', 8 ),

('Rafaela Santos', 'feminino', 6),
('Iris','feminino',32),
('Kesia', 'feminino', 25),
('Kelly', 'feminino', 17),
('Barbara', 'feminino', 26),
('Rossin', 'feminino', 22),
('Amanda Santos', 'feminino', 1),
('Gabriella Silva', 'feminino', 8),
('Kaylaine', 'feminino', 4),
('Larissa Suryan', 'feminino', 16),
('Mariana Santos', 'feminino', 6),
('Maria Eduarda', 'feminino', 32),
('Samia', 'feminino', 3),
('Alice', 'feminino', 6),
('Neves', 'feminino', 1),
('Florinda', 'feminino', 16),

('Mariana rios', 'feminino', 2),
('Simone','feminino',14),
('Mayara', 'feminino', 1),
('Simaria', 'feminino', 5),
('Leticia Sobrinho', 'feminino', 24),
('Luana Andrade', 'feminino', 33),
('Stefanie', 'feminino', 31),
('Mislaine', 'feminino', 30),
('Fernanda Lima', 'feminino', 20),
('Victoria', 'feminino', 10),
('Debora', 'feminino', 2),
('Juliana', 'feminino', 5),
('Julia Freitas', 'feminino', 13),
('Micaelly', 'feminino', 12),
('Sabrina', 'feminino', 2),
('Leumas', 'feminino', 17),

('William', 'masculino', 1 ),
('Robson', 'masculino',22 ),
('Edson', 'masculino', 1 ),
('Zlantan', 'masculino', 5 ),
('Brian', 'masculino', 21),
('Filipe', 'masculino', 34 ),
('Dagoberto', 'masculino', 22),
('William', 'masculino', 1 ),
('Fernando Araujo', 'masculino', 5 ),
('Pietro', 'masculino', 2 ),
('Xavier', 'masculino', 11 ),
('Scott', 'masculino', 7),
('Andre luiz', 'masculino', 11 ),
('Rafael Bento', 'masculino', 6),
('Ruiz', 'masculino', 2 ),
('Nava', 'masculino', 19 ),

('Beatriz Telles', 'feminino', 21),
('Leticia Silva','feminino',1),
('Alice Souza', 'feminino', 12),
('Giovanna Cris', 'feminino', 25),
('Ellen', 'feminino', 4),
('Dellafina', 'feminino', 32),
('Amanda Maria', 'feminino', 21),
('Daniella', 'feminino', 31),
('Karen Lima', 'feminino', 22),
('Angel', 'feminino', 20),
('Iandra', 'feminino', 21),
('Aristea', 'feminino', 32),
('Fabiane', 'feminino', 16),
('Silvana', 'feminino', 18),
('Brina', 'feminino', 12),
('Iasmin', 'feminino', 7),

('Marciano', 'masculino', 12 ),
('Mateus Dias', 'masculino',2 ),
('Edson Arantes', 'masculino', 11 ),
('Gabriel Barbosa', 'masculino', 25 ),
('Olivio', 'masculino', 2),
('Lucas fernandes', 'masculino', 30 ),
('Moreira', 'masculino', 20),
('Alex Dias', 'masculino', 21 ),
('Roberto', 'masculino', 18 ),
('Beto', 'masculino', 21 ),
('Vitalino', 'masculino', 2 ),
('Thiagones', 'masculino', 17),
('Lukas', 'masculino', 29 ),
('kenedy', 'masculino', 27),
('Gerson', 'masculino', 19 ),
('Jean Motta', 'masculino', 7 ),

('Guida', 'feminino', 11),
('Ester Morimoto','feminino',32),
('Heloisa Barbosa', 'feminino', 12),
('Conceição', 'feminino', 23),
('Gleice Torres', 'feminino', 12),
('Sabrina melo', 'feminino', 15),
('Amanda fernandes', 'feminino', 25),
('Manuela Martins', 'feminino', 33),
('Weslaine', 'feminino', 27),
('Camilla', 'feminino', 2),
('Karen Oliveira', 'feminino', 11),
('Tyfanni', 'feminino', 32),
('Lizandra', 'feminino', 1),
('Isabella Rizzato', 'feminino', 18),
('Isadora', 'feminino', 3),
('Jadila', 'feminino', 32),

('Marcelo Mattos', 'masculino', 11 ),
('Sanchez', 'masculino',22 ),
('Suaso', 'masculino', 14 ),
('Vargas', 'masculino', 2 ),
('Bolt', 'masculino', 5),
('Daddy', 'masculino', 3 ),
('Justin', 'masculino', 2),
('Martin ', 'masculino', 5 ),
('Ricardo Oliveira', 'masculino', 23 ),
('Graciano', 'masculino', 32 ),
('Otavio', 'masculino', 11 ),
('Senor', 'masculino', 22),
('Carlos Massa', 'masculino', 25 ),
('Bolanos', 'masculino', 21),
('Bob', 'masculino', 10 ),
('Samuel', 'masculino', 27 )














select * from atleta

drop table competicao
drop table paises
drop table atleta
drop table provas

create table provas(
codigoProva int,
NomeProva varchar(100),
tipoProva char(2),--pra fazer alguma manipulação do tipo m=metros s=segundos ou hora, 
--isso permite eu colocar apenas um resultado na table resultado pois, ai numa function posso manipular, se for s eu converto pra o formato desejado
sexoProva varchar(10)
primary key (codigoProva)
)
select provas.NomeProva
from provas



insert into provas values 
(1, 'lançamento do dardo', 'SA','feminino'),
(2, 'salto em distancia','SA','masculino'),
(3, 'salto com vara' ,'SA' ,'masculino'),
(4, '400m com barreiras', 'C','masculino'),
(5, '100m', 'C','feminino' ),
(6, '3000m','C' ,'masculino'),
(7, 'lançamento do disco' ,'SA','masculino' ),
(8, '3000m com obstaculos', 'C' ,'feminino'),
(9, ' salto triplo','SA','feminino' ),
(10,'400m','C' , 'masculino'),
(11, '800m','C' , 'feminino'),
(12, '800m','C' , 'masculino'),
(13, '200m','C' , 'feminino'),
(14, '200m','C' , 'masculino')

select * from provas

SELECT *  FROM competicao
drop table competicao
create table competicao(codigoid int identity primary key,
codigoProva int,
codigoAtleta int,
fase varchar(100),
bateria int,
resultado varchar(50)
--bateria decimal (7,1),-- fase cade inicial final
foreign key (codigoProva) References provas(codigoProva),
foreign key (codigoAtleta) References atleta(codigoAtleta))

insert into competicao values 
(1,1,'inicial', 1, 23.0),
(1,2,'inicial', 1, 29.2),
(1,3,'inicial', 1, 25.1),
(1,4,'inicial', 1, 20.2),
(1,5,'inicial', 1, 22.1),
(1,6,'inicial', 1, 22.4),
(1,7,'inicial', 1, 23.5),
(1,8,'inicial', 1, 12.1),
(1,9,'inicial', 1, 26.1),
(1,10,'inicial', 1, 23.3),
(1,11,'inicial', 1, 25.3),
(1,12,'inicial', 1, 26.2),
(1,13,'inicial', 1, 26.7),
(1,14,'inicial', 1, 22.7),
(1,15,'inicial', 1, 27.3),
(1,16,'inicial', 1, 27.0),

(2,17,'inicial', 1, 5.6),
(2,18,'inicial', 1, 5.4),
(2,19,'inicial', 1, 5.1),
(2,20,'inicial', 1, 5.2),
(2,21,'inicial', 1, 6.0),
(2,22,'inicial', 1, 5.0),
(2,23,'inicial', 1, 4.5),
(2,24,'inicial', 1, 5.1),
(2,25,'inicial', 1, 6.1),
(2,26,'inicial', 1, 5.3),
(2,27,'inicial', 1, 6.3),
(2,28,'inicial', 1, 6.2),
(2,29,'inicial', 1, 4.7),
(2,30,'inicial', 1, 6.7),
(2,31,'inicial', 1, 7.3),
(2,32,'inicial', 1, 7.0),




(3,33,'inicial', 1, 1.0),
(3,34,'inicial', 1, 1.1),
(3,35,'inicial', 1, 1.2),
(3,36,'inicial', 1, 1.0),
(3,37,'inicial', 1, 0.5),
(3,38,'inicial', 1, 0.4),
(3,39,'inicial', 1, 0.6),
(3,40,'inicial', 1, 5.1),
(3,41,'inicial', 1, 6.1),
(3,42,'inicial', 1, 5.3),
(3,43,'inicial', 1, 6.3),
(3,44,'inicial', 1, 6.2),
(3,45,'inicial', 1, 4.7),
(3,46,'inicial', 1, 6.7),
(3,47,'inicial', 1, 7.3),
(3,48,'inicial', 1, 7.0),

(4,49,'inicial', 1, 5.6),
(4,50,'inicial', 1, 5.4),
(4,51,'inicial', 1, 5.1),
(4,52,'inicial', 1, 5.2),
(4,53,'inicial', 1, 6.0),
(4,54,'inicial', 1, 5.0),
(4,55,'inicial', 1, 4.5),
(4,56,'inicial', 1, 5.1),
(4,57,'inicial', 1, 6.1),
(4,58,'inicial', 1, 5.3),
(4,59,'inicial', 1, 6.3),
(4,60,'inicial', 1, 6.2),
(4,61,'inicial', 1, 4.7),
(4,62,'inicial', 1, 6.7),
(4,63,'inicial', 1, 7.3),
(4,64,'inicial', 1, 7.0),

(5,65,'inicial', 1, 5.6),
(5,66,'inicial', 1, 5.4),
(5,67,'inicial', 1, 5.1),
(5,68,'inicial', 1, 5.2),
(5,69,'inicial', 1, 6.0),
(5,70,'inicial', 1, 5.0),
(5,71,'inicial', 1, 4.5),
(5,72,'inicial', 1, 5.1),
(5,73,'inicial', 1, 6.1),
(5,74,'inicial', 1, 5.3),
(5,75,'inicial', 1, 6.3),
(5,76,'inicial', 1, 6.2),
(5,77,'inicial', 1, 4.7),
(5,78,'inicial', 1, 6.7),
(5,79,'inicial', 1, 7.3),
(5,80,'inicial', 1, 7.0),

(6,81,'inicial', 1, 5.6),
(6,82,'inicial', 1, 5.4),
(6,83,'inicial', 1, 5.1),
(6,84,'inicial', 1, 5.2),
(6,85,'inicial', 1, 6.0),
(6,86,'inicial', 1, 5.0),
(6,87,'inicial', 1, 4.5),
(6,88,'inicial', 1, 5.1),
(6,89,'inicial', 1, 6.1),
(6,90,'inicial', 1, 5.3),
(6,91,'inicial', 1, 6.3),
(6,92,'inicial', 1, 6.2),
(6,93,'inicial', 1, 4.7),
(6,94,'inicial', 1, 6.7),
(6,95,'inicial', 1, 7.3),
(6,96,'inicial', 1, 7.0),

(6,97,'inicial', 1, 7.0),
(7,98,'inicial', 1, 5.6),
(7,99,'inicial', 1, 5.4),
(7,100,'inicial', 1, 5.4),
(7,101,'inicial', 1, 5.1),
(7,102,'inicial', 1, 5.2),
(7,103,'inicial', 1, 6.0),
(7,104,'inicial', 1, 5.0),
(7,105,'inicial', 1, 4.5),
(7,106,'inicial', 1, 5.1),
(7,107,'inicial', 1, 6.1),
(7,108,'inicial', 1, 5.3),
(7,109,'inicial', 1, 6.3),
(7,110,'inicial', 1, 6.2),
(7,111,'inicial', 1, 4.7),
(7,112,'inicial', 1, 6.7),
(7,113,'inicial', 1, 7.3),
(7,114,'inicial', 1, 7.0),

(8,115,'inicial', 1, 7.0),
(8,116,'inicial', 1, 5.6),
(8,117,'inicial', 1, 5.4),
(8,118,'inicial', 1, 5.1),
(8,119,'inicial', 1, 5.2),
(8,120,'inicial', 1, 6.0),
(8,121,'inicial', 1, 5.0),
(8,122,'inicial', 1, 4.5),
(8,123,'inicial', 1, 5.1),
(8,124,'inicial', 1, 6.1),
(8,125,'inicial', 1, 5.3),
(8,126,'inicial', 1, 6.3),
(8,127,'inicial', 1, 6.2),
(8,128,'inicial', 1, 4.7),
(8,129,'inicial', 1, 6.7),
(8,130,'inicial', 1, 7.3),


(9,131,'inicial', 1, 5.6),
(9,132,'inicial', 1, 5.4),
(9,133,'inicial', 1, 5.1),
(9,134,'inicial', 1, 5.2),
(9,135,'inicial', 1, 6.0),
(9,136,'inicial', 1, 5.0),
(9,137,'inicial', 1, 4.5),
(9,138,'inicial', 1, 5.1),
(9,139,'inicial', 1, 6.1),
(9,140,'inicial', 1, 5.3),
(9,141,'inicial', 1, 6.3),
(9,142,'inicial', 1, 6.2),
(9,143,'inicial', 1, 4.7),
(9,144,'inicial', 1, 6.7),
(9,145,'inicial', 1, 7.3),
(9,146,'inicial', 1, 7.0),

(10,147,'inicial', 1, 5.6),
(10,148,'inicial', 1, 5.4),
(10,149,'inicial', 1, 5.1),
(10,150,'inicial', 1, 5.2),
(10,151,'inicial', 1, 6.0),
(10,152,'inicial', 1, 5.0),
(10,153,'inicial', 1, 4.5),
(10,154,'inicial', 1, 5.1),
(10,155,'inicial', 1, 6.1),
(10,156,'inicial', 1, 5.3),
(10,157,'inicial', 1, 6.3),
(10,158,'inicial', 1, 6.2),
(10,159,'inicial', 1, 4.7),
(10,160,'inicial', 1, 6.7),
(10,161,'inicial', 1, 7.3),
(10,162,'inicial', 1, 7.0),


(11,163,'inicial', 1, 5.6),
(11,164,'inicial', 1, 5.4),
(11,165,'inicial', 1, 5.1),
(11,166,'inicial', 1, 5.2),
(11,167,'inicial', 1, 6.0),
(11,168,'inicial', 1, 5.0),
(11,169,'inicial', 1, 4.5),
(11,170,'inicial', 1, 5.1),
(11,171,'inicial', 1, 6.1),
(11,172,'inicial', 1, 5.3),
(11,173,'inicial', 1, 6.3),
(11,174,'inicial', 1, 6.2),
(11,175,'inicial', 1, 4.7),
(11,176,'inicial', 1, 6.7),
(11,177,'inicial', 1, 7.3),
(11,178,'inicial', 1, 7.0),


(12,179,'inicial', 1, 5.6),
(12,180,'inicial', 1, 5.4),
(12,181,'inicial', 1, 5.1),
(12,182,'inicial', 1, 5.2),
(12,183,'inicial', 1, 6.0),
(12,184,'inicial', 1, 5.0),
(12,185,'inicial', 1, 4.5),
(12,186,'inicial', 1, 5.1),
(12,187,'inicial', 1, 6.1),
(12,188,'inicial', 1, 5.3),
(12,189,'inicial', 1, 6.3),
(12,190,'inicial', 1, 6.2),
(12,191,'inicial', 1, 4.7),
(12,192,'inicial', 1, 6.7),
(12,193,'inicial', 1, 7.3),
(12,194,'inicial', 1, 7.0),


(13,195,'inicial', 1, 5.6),
(13,196,'inicial', 1, 5.4),
(13,197,'inicial', 1, 5.1),
(13,198,'inicial', 1, 5.2),
(13,199,'inicial', 1, 6.0),
(13,200,'inicial', 1, 5.0),
(13,201,'inicial', 1, 4.5),
(13,202,'inicial', 1, 5.1),
(13,203,'inicial', 1, 6.1),
(13,204,'inicial', 1, 5.3),
(13,205,'inicial', 1, 6.3),
(13,206,'inicial', 1, 6.2),
(13,207,'inicial', 1, 4.7),
(13,208,'inicial', 1, 6.7),
(13,209,'inicial', 1, 7.3),
(13,210,'inicial', 1, 7.0),


(14,211,'inicial', 1, 5.6),
(14,212,'inicial', 1, 5.4),
(14,213,'inicial', 1, 5.1),
(14,214,'inicial', 1, 5.2),
(14,215,'inicial', 1, 6.0),
(14,216,'inicial', 1, 5.0),
(14,217,'inicial', 1, 4.5),
(14,218,'inicial', 1, 5.1),
(14,219,'inicial', 1, 6.1),
(14,220,'inicial', 1, 5.3),
(14,221,'inicial', 1, 6.3),
(14,222,'inicial', 1, 6.2),
(14,223,'inicial', 1, 4.7),
(14,224,'inicial', 1, 6.7)



drop table competicao 

select Distinct(competicao.fase)
from competicao 
ORDER BY competicao.fase desc

select Distinct(competicao.bateria)
from competicao
order by competicao.bateria asc


insert into competicao (codigoProva,codigoAtleta,fase,bateria,resultado)
values(1,2,'Final',2,'11'),
(3,3,'Inicial',1,'01:20:20.085')

select * from atleta
select * from provas
select * from competicao

--ISNULL(competicao.resultadoT,competicao.resultadoM),
--parecido com o select * from competicao mas com os nomes da prova e atleta
select provas.NomeProva,atleta.nomeAtleta,competicao.fase,competicao.bateria,competicao.resultado
from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where competicao.bateria=2 



select provas.NomeProva,atleta.nomeAtleta,competicao.fase,competicao.bateria,competicao.resultado
from competicao
inner join provas
on competicao.codigoProva=provas.codigoProva
inner join atleta
on competicao.codigoAtleta=atleta.codigoAtleta
where competicao.bateria=2 and provas.tipoProva like 'C' and cast(

select * from competicao
select cast(competicao.resultado as time)  
from competicao
where competicao.codigoid=4--codigoProva=4 and competicao.codigoProva=4

SELECT CONVERT(time(3) ,competicao.resultado)
from competicao
where competicao.codigoAtleta=2

--pega o melhor resultado do meters e inicial de determinado atleta
select atleta.nomeAtleta,max(competicao.resultadoM)
from atleta
inner join competicao
on atleta.codigoAtleta=competicao.codigoAtleta
where competicao.fase like 'Inicial'
group by atleta.nomeAtleta





insert into competicao values
(2,3,1.20)




select atleta.codigoAtleta,Atleta.nomeAtleta, provas.NomeProva, provas.codigoProva,competicao.bateria, paises.CoiPais 
from atleta
inner join competicao on atleta.codigoAtleta = competicao.codigoAtleta
inner join provas on provas.codigoProva = competicao.codigoProva
inner join paises on atleta.CodigoPais = paises.CodigoPais
order by competicao.bateria



