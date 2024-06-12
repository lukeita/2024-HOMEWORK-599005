package it.uniroma3.diadia.ambienti;

public enum Direzione {
	nord{
		@Override
		public Direzione opposta() {
			return sud;
		}

		@Override
		public String verso() {
			return "nord";
		}
	}, 
	sud{
		@Override
		public Direzione opposta() {
			return nord;
		}

		@Override
		public String verso() {
			return "sud";
		}		
	}, 
	ovest{
		@Override
		public Direzione opposta() {
			return est;
		}

		@Override
		public String verso() {
			return "ovest";
		}	
	}, 
	est{
		@Override
		public Direzione opposta() {
			return ovest;
		}

		@Override
		public String verso() {
			return "est";
		}	
	};
	public abstract Direzione opposta();

	public abstract String verso();
	
}
