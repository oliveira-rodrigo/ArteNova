CREATE TABLE IF NOT EXISTS [Banners] (
    [ID] INTEGER NOT NULL,
    [Imagem] TEXT
);

CREATE TABLE IF NOT EXISTS [Departamentos] (
  [ID] INTEGER NOT NULL,
  [Empresa_ID] INTEGER NOT NULL,
  [Nome] TEXT,
  [Imagem] TEXT
);


CREATE TABLE IF NOT EXISTS [Empresas] (
  [ID] INTEGER NOT NULL,
  [Nome] TEXT,
  [Descricao] TEXT,
  [Contato] TEXT,
  [Imagem] TEXT,
  [ImagemNoticia] TEXT
);

CREATE TABLE IF NOT EXISTS [Empresa_Representante] (
  [Empresa_ID] INTEGER NOT NULL,
  [Representante_ID] INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS [Empresa_Acabamento] (
  [Acabamento_ID] INTEGER NOT NULL,
  [Empresa_ID] INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS [Fotos] (
  [ID] INTEGER NOT NULL,
  [Produto_ID] INTEGER,
  [Imagem] TEXT,
  [Tecnica] INTEGER
);


CREATE TABLE IF NOT EXISTS [Grupos] (
  [ID] INTEGER NOT NULL,
  [Descricao] TEXT,
  [Imagem_1] TEXT,
  [Imagem_2] TEXT,
  [Imagem_3] TEXT
);


CREATE TABLE IF NOT EXISTS [Produtos] (
  [ID] INTEGER NOT NULL,
  [Departamento_ID] INTEGER NOT NULL,
  [Nome] TEXT,
  [Imagem] TEXT,
  [Descricao] TEXT
);


CREATE TABLE IF NOT EXISTS [ProdutosInformacoes] (
  [ID] INTEGER NOT NULL,
  [Produto_ID] INTEGER NOT NULL,
  [Nome] TEXT,
  [Altura] TEXT,
  [Largura] TEXT,
  [Profundidade] TEXT,
  [MedidaInterna] TEXT
);
  

CREATE TABLE IF NOT EXISTS [ProdutosFavoritos] (
    [ID] INTEGER PRIMARY KEY AUTOINCREMENT,
    [Produto_ID] INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS [ProdutosPedidos] (
    [ID] INTEGER PRIMARY KEY AUTOINCREMENT,
    [Produto_ID] INTEGER NOT NULL
);
  

CREATE TABLE IF NOT EXISTS [Representantes] (
  [ID] INTEGER NOT NULL,
  [Nome] TEXT,
  [Email] TEXT,
  [Senha] TEXT
);
  
  
CREATE TABLE IF NOT EXISTS [CategoriaAcabamento] (
  [ID] INTEGER NOT NULL,
  [Nome] TEXT
);


CREATE TABLE IF NOT EXISTS [Acabamentos] (
  [ID] INTEGER NOT NULL,
  [CategoriaAcabamento_ID] INTEGER NOT NULL,
  [Nome] TEXT,
  [Descricao] TEXT,
  [Imagem] TEXT
);


CREATE TABLE IF NOT EXISTS [Noticias] (
    [ID] INTEGER NOT NULL,
    [Empresa_ID] INTEGER NOT NULL,
    [Titulo] TEXT,
    [Descricao] TEXT,
    [Data] DATETIME
);


CREATE TABLE IF NOT EXISTS [Versoes] (
  [ID] INTEGER NOT NULL,
  [VersaoServidor] REAL NOT NULL,
  [VersaoAplicativo] REAL NOT NULL,
  [Data] DATETIME
);


