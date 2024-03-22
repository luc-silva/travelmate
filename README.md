# travelmate

Travelmate é um projeto de gerênciamento de hotel, feito em Java, utilizando o JDBC e MySql.
Possui duas entidades simples, Sala e Cliente, que estão relacionadas entre sí.

O Usuário pode criar novas salas, adicionar clientes, reservar um quarto a um cliente e atualizar as informações do quarto.
<!---
SELECT * FROM (
	 SELECT DISTINCT MODPA.ID_MODULO_FILIAL 	"idModuloFilial", 
		             MODPA.ID_PROD 				"idProd", 
		             CPR.nm_prod 				"nmProd", 
		             MODA.ID_MODULO 			"idModulo",
		             FI.SG_FIL 					"padrao", 
		             MODAF.CD_FIL 			    "cdFil", 
		             MODA.DS_MODULO             "descrModulo",
		             MODPA.QT_IDEAL_ESTQ_VM     "qtIdealEstqVm",
		             MODPA.QT_IDEAL_ESTQ_MIN    "qtIdealEstqMin",
		             MODPA.QT_IDEAL_INCREMENTO  "qtIdealIncremento",
		             MODPA.QT_IDEAL_ESTQ_MAX    "qtIdealEstqMax",
		             MODPA.IN_TIPO_EXPOSICAO    "inTipoExposicao",
		             MODPA.QT_PROD_EMPILHADO    "qtProdEmpilhado",
		             MODPA.ID_PROD_SUBST        "idProdSubst"
		            --COUNT(*) OVER() "totalRows"
	    FROM MODPA, CPR, MODAF, MODA, Filial fi
	    WHERE   CPR.ID_PROD = MODPA.ID_PROD
	        AND MODPA.ID_MODULO_FILIAL = MODAF.ID_MODULO_FILIAL
	        AND MODAF.CD_FIL in (SELECT CD_FIL FROM GC.CONFIG_FILIAL_PADRAO_QID WHERE DT_CANC IS NULL)
	        AND MODAF.ID_MODULO        = MODA.ID_MODULO 
	        AND MODAF.CD_FIL = fi.CD_FIL 
	        AND CPR.CD_GEP = DECODE(1, 999, CPR.CD_GEP, 1) -- N PRECISO PARA ACHAR UM SÓ
	        AND CPR.DT_CANC IS NULL
	        AND MODPA.IN_TIPO_EXPOSICAO IS NOT NULL
			--AND MODPA.ID_PROD  = DECODE(38628 , NULL, MODPA.ID_PROD, 38628)
			--AND MODA.ID_MODULO = 427 --adicionado agora para achar um só
	    UNION ALL 
	    SELECT NDS.ID_PROD, filiais_ativas.CD_FIL
	    FROM NINFO.NI_DETALHAMENTO_SIM1 nds
	    CROSS JOIN (
			SELECT DISTINCT MODAF.CD_FIL
			FROM MODAF
			WHERE MODAF.CD_FIL IN (
				SELECT CD_FIL 
				FROM GC.CONFIG_FILIAL_PADRAO_QID 
				WHERE DT_CANC IS NULL
			)
	    ) filiais_ativas
	   	--ORDER BY MODPA.ID_PROD, ate.CD_FIL
	    --OFFSET 2 * (1-1) ROWS
    )
>
