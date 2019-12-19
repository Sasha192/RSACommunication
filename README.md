# RSA Communication

* [Project purpose](#purpose)
* [Project structure](#structure)
* [Authors](#authors)
* [Bibliography](#bibliography)

# <a name="purpose"></a>Project purpose

Let : 
* public Exponent ( publicE ) = <img src = "https://render.githubusercontent.com/render/math?math=%5C%20e%20%5C%20%3A%20%5C%20gcd(e%2C%20%5Cphi(n))%20%5Cequiv%201%20%5C%20mod%20%5C%20n">
* private D key ( privateD ) = <img src = "https://render.githubusercontent.com/render/math?math=%5C%20d%20%3A%20%5C%20d%20*%20e%20%5Cequiv%201%20%5C%20mod%20%5C%20n%20">
* public Modulus ( publicMod ) = <img src = "https://render.githubusercontent.com/render/math?math=%5C%20n%20%5C%20%3A%20%5C%20n%20%3D%20p%20*%20q">
, that p and q - big prime numbers with more than 256 bits.
* <img src = "https://render.githubusercontent.com/render/math?math=%5C%20RSA%20%3A%20M%20%5Crightarrow%20C"> , where M - all messages, C - all cipher texts, RSA - function.
* <img src="https://render.githubusercontent.com/render/math?math=%5C%20euler%20%5C%20function%20%5C%20%5Cphi%20(n)%20%3A%20%5Cmathbb%7BN%7D%20%5Crightarrow%20%5Cmathbb%7BN%7D)">



Understanding specificity of RSA encryption : 

* For every Client ( customer ) we construct CustomerServiceImpl class - Client private field, 
that will store public and private keys ( privateD, publicE, publicMod ). It is better, that Client will not store any information about his cryptosystem.
CustomerServiceImpl is black box for customer : 
customer only receive cipher text on given keys,
text ( that want to encrypt ) and get decrypted text on given cipher text.
* Generating prime public Exponent with more  
or equals than 10 bits.
 We used so because of Coppersmith's attack on low public Exponent. Also it not recommended to generate large publicE or near <img src="https://render.githubusercontent.com/render/math?math=%5Cphi(n)%20%2F2%20">
. For example, assume htth?math=e%20%3D%20%5Cphi(n)%20%2F%202<img src ="https://render.githubusercontent.com/render/math?math=e%20%3D%20%5Cphi(n)%20%2F%202%20%2B%201">, hence for every M satisfy :
<img src="https://render.githubusercontent.com/render/math?math=M%5E%7Be%7D%20%5Cequiv%20M%5E%7B%5Cphi(n)%2F2%20%2B%201%7D%20%5Cequiv%20M%5E%7B%5Cphi(n)%2F2%7D*M%20%5Cequiv%20M%20%5C%20mod%20%5C%20n">

* Correctness of implementation was tested with remote server ( see class Server, ServerService )

# <a name="structure"></a>Tech stack
* Java 8
* Maven 4.0.0
* JUnit 4
* JSON-Simple 1.1



# <a name="authors"></a>Authors
* [Oleksandr Bunin](https://github.com/Sasha192)

# <a name="bibliography"></a>Bibliography

[1] Glenn Durfee. Cryptanalysis of RSA using Algebraic and Lattice methods.
A dissertation submitted to the department of Computer Science and the committee on graduate studies of Stanford university in partial fulfillment of the requirements
 for the degree of Doctor of Philosophy, 2002.
 
[2] M. Bellare and P. Rogaway. Optimal asymmetric encryption. In proceedings
Eurocrypt ’94, Lecture Notes in Computer Science, vol. 950, Springer-Verlag,
pp. 92–111, 1994.

[3] D. Boneh. Twenty years of attacks on the RSA cryptosystem. Notices of
the AMS, 46(2):203–213, 1999

[4] D. Coppersmith, M. Franklin, J. Patarin, and M. Reiter. Low exponent
RSA with related messages. In proceedings Eurocrypt ’96, Lecture Notes in
Computer Science, vol. 1070, Springer-Verlag, pp. 1–9, 1996

[5] Rivest R., Shamir A., Adleman L. A method for obtaining digital signatures and public-key cryptosystems. ACM — New York City: ACM, 1978. — Vol. 21, Iss. 2. — P. 120–126.