// Bibliotecas e Tipos de dados
include <stdio.h>
include <stdlib.h> 
include <ctype.h> 

typedef char* string;
typedef enum {FALSE,TRUE} bool;

// Criando a Pilha

// A pilha residirá no vetor stk[0...stkcount-1], 
// Sendo stkcount - 1 o índice do tipo da pilha.
// As variáveis stk[] e stkcount serão globais.
#define MaxStackSize 100
double stk[MaxStackSize];
int stkcount;

// Operador empilha: coloca elemento na pilha 
void Push( double element)
{
    if(stkcount == MaxStackSize)
    printf(" Tamanho da pilha excedido.\n");
    exit(EXIT_FAILURE);
}

// Operador desempilha: retirar o elemento que está no topo da pilha
double Pop(void)
{
    if (stkcount == 0) {
        printf(" Pop de uma pilha vazia")
        exit( EXIT_FAILURE);
   }
   return stk[--stkcount];
}

// Simulaçao da Calculadora

void ApplyOperator( char op);
void DisplayStack(void);
string getline(void);

int main(void)
{
   string line;
   char ch;

   printf( "RPN Simulacao de Calculadora\n");
   stkcount = 0;
   while (TRUE) {
      printf( "> ");
      line = getLine();
      ch = toupper( line[0]);
      switch (ch) {
         case 'Q': return 0;
         case 'C': stkcount = 0; break;
         case 'S': DisplayStack(); break;
         default : if (isdigit( ch)) 
                      Push( atof( line));
                   else
                      ApplyOperator( ch);
                   break;
      }
   }
}
// Essa função aplicará o operador op aos dois operandos que será usado no topo da pilha.
// A função supos que o operador não será dividido por zero.

void ApplyOperator( char op)
{
   double opesquerdo, opdireito, resultado;
   
   opdireito = Pop(); // operando direito
   opesquerdo = Pop(); // operando esquerdo
   switch (op) {
      case '+': result = opesquerdo + opdireito; break;
      case '-': result = opesquerdo - opdireito; break;
      case '*': result = opesquerdo * opdireito; break;
      case '/': result = opesquerdo / opdireito; break;
      default : printf( "Operacao Invalida\n"); 
                exit( EXIT_FAILURE);
   }
   printf( "%g\n", resultado);
   Push( resultado);
}
// Imprimira o conteudo da pilha
void DisplayStack( void)
{
   int i;
   
   printf( "Pilha: ");
   if (stkcount == 0) 
      printf( "vazia\n");
   else {
      for (i = 0; i < stkcount; i++) {
         if (i > 0) printf( ", ");
         printf( "%g", stk[i]);
      }
      printf( "\n");
   }
}
// Lê uma linha do teclado e transforma em string.
string getLine( void)
{
   string line;
   int n, ch;
   
   n = 0;
   line = malloc( 100+1);
   if (line == NULL) exit( EXIT_FAILURE);
   while ((ch = getc( stdin)) != '\n') {
      if (n >= 100) {
         free( line);
         exit( EXIT_FAILURE);
      }
      line[n++] = ch;
   }
   line[n] = '\0';
   return line;
}