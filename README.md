# travelmate

Travelmate é um projeto de gerênciamento de hotel, feito em Java, utilizando o JDBC e MySql.
Possui duas entidades simples, Sala e Cliente, que estão relacionadas entre sí.

O Usuário pode criar novas salas, adicionar clientes, reservar um quarto a um cliente e atualizar as informações do quarto.
<!---
(SELECT DISTINCT 
    MODPA.ID_MODULO_FILIAL  AS "idModuloFilial", 
    MODPA.ID_PROD           AS "idProd", 
    CPR.nm_prod             AS "nmProd", 
    MODA.ID_MODULO          AS "idModulo",
    FI.SG_FIL               AS "padrao", 
    MODAF.CD_FIL            AS "cdFil", 
    MODA.DS_MODULO          AS "descrModulo",
    MODPA.QT_IDEAL_ESTQ_VM  AS "qtIdealEstqVm",
    MODPA.QT_IDEAL_ESTQ_MIN AS "qtIdealEstqMin",
    MODPA.QT_IDEAL_INCREMENTO AS "qtIdealIncremento",
    MODPA.QT_IDEAL_ESTQ_MAX AS "qtIdealEstqMax",
    MODPA.IN_TIPO_EXPOSICAO AS "inTipoExposicao",
    MODPA.QT_PROD_EMPILHADO AS "qtProdEmpilhado",
    MODPA.ID_PROD_SUBST     AS "idProdSubst"
FROM 
    MODPA
JOIN 
    CPR ON CPR.ID_PROD = MODPA.ID_PROD
JOIN 
    MODAF ON MODPA.ID_MODULO_FILIAL = MODAF.ID_MODULO_FILIAL
JOIN 
    MODA ON MODAF.ID_MODULO = MODA.ID_MODULO
JOIN 
    Filial FI ON MODAF.CD_FIL = FI.CD_FIL
JOIN (
    SELECT DISTINCT 
        MODAF.CD_FIL
    FROM 
        MODAF
    WHERE 
        MODAF.CD_FIL IN (
            SELECT CD_FIL 
            FROM GC.CONFIG_FILIAL_PADRAO_QID 
            WHERE DT_CANC IS NULL
        )
) filiais_ativas ON MODAF.CD_FIL = filiais_ativas.CD_FIL
WHERE 
    CPR.DT_CANC IS NULL
    AND MODPA.IN_TIPO_EXPOSICAO IS NOT NULL
    AND CPR.CD_GEP = DECODE(1, 999, CPR.CD_GEP, 1))
UNION ALL
(SELECT 
    NDS.ID_PROD, 
    filiais_ativas.CD_FIL
FROM 
    NINFO.NI_DETALHAMENTO_SIM1 NDS
CROSS JOIN (
    SELECT DISTINCT 
        MODAF.CD_FIL
    FROM 
        MODAF
    WHERE 
        MODAF.CD_FIL IN (
            SELECT CD_FIL 
            FROM GC.CONFIG_FILIAL_PADRAO_QID 
            WHERE DT_CANC IS NULL
        )
) filiais_ativas)

>
