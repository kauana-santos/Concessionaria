# Explicação

**Carro**
 
Ano de fabricação e ano do modelo, ficaram como Integer, pois é para armazenar apenas o ano, não a data inteira;

Foi criada uma ENUM para estabelecer se o carro é novo ou seminovo

A placa permite nulo, pois os carros novos podem não ter placa

Placa e chassi são únicos, pois isso o unique foi definido 

---

**Cliente**

O CPF foi definido como unique, para que não tenha duplicidade.
