import java.util.Scanner;

public class RepresentacaoDeDados
{
	//Vetor de dados
	static int[] iValor = new int[8];

	static Scanner leitor = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		int opcaoEntrada = -1; 
		iValor[0] = 1;
		iValor[1] = 1;
		iValor[2] = 1;
		iValor[3] = 1;
		iValor[4] = 0;
		iValor[5] = 1;
		iValor[6] = 1;
		iValor[7] = 0;
		
		while(opcaoEntrada != 0)
		{
			//Scanner l = new Scanner(System.in);
			menu();
			opcaoEntrada = Integer.parseInt(leitor.nextLine());
			
			if(opcaoEntrada == 1) // Entrada de dados binário
			{
				entradaDeDadosBinarios();
			} else if(opcaoEntrada == 2) // entrada de dados decimal
			{
				entradaDeDadosDecimal();
			} else if(opcaoEntrada == 3) // converte para decimal
			{
				converterParaDecimal();
			}
		}
		/*
		converterParaDecimal();
		converterParaDecimalDeExcesso127();
		converterParaDecimalDeSM();
		converterParaDecimalDeComplemento2();
		converterParaDecimalDePontoFlutuante();
		*/
	}
	
	private static void entradaDeDadosDecimal() {
		// TODO Auto-generated method stub
		
	}

	public static void menu()
	{
		System.out.println(" ++++++ Conversor binário ++++++\n\n");
		System.out.println("Selecione uma das opções abaixo:");
		System.out.println("\t1 para inserir um valor binário.");
		System.out.println("\t2 para inserir um valor decimal.");
		System.out.println("\t3 para converter para decimal em BCD8421.");
		System.out.println("\t4 para converter para decimal em Sinal por Magnitude.");
		System.out.println("\t5 para converter para decimal em Excesso 127.");
		System.out.println("\t6 para converter para decimal em Complemento de 2.");
		System.out.println("\t7 para converter para binário em BCD8421.");
		System.out.println("\t8 para converter para binário em Sinal por magnitude.");
		System.out.println("\t9 para converter para binário em Excesso 127.");
		System.out.println("\t10 para converter para binário em Complemento de 2.");
		System.out.println("\t0 para sair.");
		System.out.print(">> ");
	}
	
	public static void entradaDeDadosBinarios()
	{
		// TODO Verificar a consistência dos dado inserido pelo usuário. 
		// Por exemplo, informar somente binário em 8 bits. 
		//Scanner leitor = new Scanner(System.in);
		String sEntrada = new String();
		int i = 0; 
		
		System.out.print("Digite 8 bits: ");
		sEntrada = leitor.nextLine();

		for(i = 0; i < sEntrada.length(); i++)
		{
			iValor[i] = Integer.parseInt(sEntrada.substring(i, i+1));
			System.out.println(iValor[i]);
		}
		
		//leitor.close();
	}
	
	public static void converterParaDecimalDePontoFlutuante()
	{
		int[] iValorTemp = new int[3];
		int cont = 0;
		int excesso = 0;
		int posicao = -1;
		float pf = (float) 0.0;
		
		iValorTemp[0] = iValor[4];
		iValorTemp[1] = iValor[5];
		iValorTemp[2] = iValor[6];
		
		cont = 2;
		while(cont >= 0)
		{
			excesso += iValorTemp[cont] * Math.pow(2, cont);
			cont--;
		}
		excesso = excesso - 3;
		
		cont = 3;
		while(cont >= 0)
		{
			//System.out.println(excesso);
			if(posicao + excesso >= 0)
			{
				pf += iValor[cont] * Math.pow(2, posicao + excesso);
			}
			else
			{
				pf += iValor[cont] * 1/Math.pow(2, (posicao + excesso) * -1 );
			}

			cont--;
			posicao--;
		}
		if(iValor[7] == 1)
			pf = pf * -1;
		
		mostrarValor();
		System.out.println("(PF) = " + pf + "(10)");
	}
	
	public static void converterParaDecimalDeComplemento2()
	{
		int cont = 7;
		int complemento2 = 0;
		
		int[] iValorTemp = new int[8];
		if(iValor[7] == 0)
		{
			converterParaDecimal();
		} else
		{
			while(cont >= 0)
			{
				if(iValor[cont] == 1)
					iValorTemp[cont] = 0;
				else
					iValorTemp[cont] = 1;
				cont--;
			}
			cont = 0;
			while(cont < 8)
			{
				if(iValorTemp[cont] == 0)
				{
					iValorTemp[cont] = 1;
					break;
				} else
				{
					iValorTemp[cont] = 0;
				}
				cont++;
			}
			
			System.out.print("[ ");
			cont = 7;
			while(cont >= 0)
			{
				System.out.print(iValorTemp[cont] + " ");
				cont--;
			}
			System.out.print("]");
			
			cont = 7;
			while(cont >= 0)
			{
				complemento2 += iValorTemp[cont] * Math.pow(2, cont);
				cont--;
			}
			
			System.out.println("(C2) = " + complemento2 + "(10)");
		}
	}

	public static void converterParaDecimalDeExcesso127()
	{
		int cont = 7;
		int bcd8421 = 0;
		mostrarValor();
		
		while(cont >= 0)
		{
			bcd8421 += iValor[cont] * Math.pow(2, cont);
			cont--;
		}
		bcd8421 = bcd8421 - 127;
		System.out.println("(E127) = " + bcd8421 + "(10)");
	}

	
	public static void converterParaDecimalDeSM()
	{
		int cont = 6;
		int sm = 0;
		mostrarValor();
		
		while(cont >= 0)
		{
			sm += iValor[cont] * Math.pow(2, cont);
			cont--;
		}
		if(iValor[7] == 1)
			sm = sm * -1;
		System.out.println("(SM) = " + sm + "(10)");
	}
	
	public static void converterParaDecimal()
	{
		int cont = 7;
		int bcd8421 = 0;
		mostrarValor();
		
		while(cont >= 0)
		{
			bcd8421 += iValor[cont] * Math.pow(2, cont);
			cont--;
		}
		System.out.println("(2) = " + bcd8421 + "(10)");
	}
	
	public static void mostrarValor()
	{
		System.out.print("[ ");
		int cont = 7;
		while(cont >= 0)
		{
			System.out.print(iValor[cont] + " ");
			cont--;
		}
		System.out.print("]");
	}
}
