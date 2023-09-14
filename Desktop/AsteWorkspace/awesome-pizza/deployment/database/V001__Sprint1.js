module.exports = {
    async up(dbUtils) {		
    	await dbUtils.db.createCollection( "ORDER_TRACKING", { validator: {
			 $jsonSchema: {
				    "required": [
				        "STATUS",
						"TICKET_NUMBER",
				        "DT_INIZIO"
				      ],
				      "properties": {
				        "STATUS": {
				          "bsonType": "string"
				        },
				        "DT_INIZIO": {
				          "bsonType": "string"
				        },
				        "DT_FINE": {
				          "bsonType": "string"
				        },
                        "ORDER_CODE": {
				          "bsonType": "string"
				        },
                        "TICKET_NUMBER": {
				          "bsonType": "int"
				        },
                        "ORDER_DETAILS": {
				          "bsonType": "object",
				          "properties": {
				                "NOME_CLIENTE": {
				                  "bsonType": "string"
				                },							  
				                "PIZZE": {
				                  "bsonType": "array"
				                }
				              }
				            }
				          }
				    }
			} , validationLevel : "strict" , validationAction: "error"
		} );
	}
}