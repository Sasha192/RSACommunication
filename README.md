# RSA Communication

* [Project purpose](#purpose)
* [Project structure](#structure)
* [Authors](#authors)
* [Bibliography](#bibliography)

# <a name="purpose"></a>Project purpose

Let : 
* public Exponent ( publicE ) = ![equation](https://latex.codecogs.com/gif.download?%5C%20e%20%3A%20gcd%28e%2C%5Cphi%20%28n%29%29)
* private D key ( privateD ) = ![equation](https://latex.codecogs.com/gif.download?%5C%20d%20%3A%20e%20*%20d%20%5Cequiv%201%20modn)
* public Modulus ( publicMod ) = ![equation](https://latex.codecogs.com/gif.download?%5C%20n%20%3A%20n%20%3D%20p%20*%20q)
, that p and q - big prime numbers with more than 256 bits.
* ![equation](https://latex.codecogs.com/gif.download?%5C%20RSA%20%3A%20M%20%5Crightarrow%20C) , where M - all messages, C - all cipher texts, RSA - function.
* ![equation](https://latex.codecogs.com/gif.download?%5C%20euler%20%5C%20function%20%5C%20%5Cphi%20%28n%29%20%3A%20%5Cmathbb%7BN%7D%20%5Crightarrow%20%5Cmathbb%7BN%7D)


Understanding specificity of RSA encryption : 

* For every Client ( customer ) we construct CustomerServiceImpl class - Client private field, 
that will store public and private keys ( privateD, publicE, publicMod ). It is better, that Client will not store any information about his cryptosystem.
CustomerServiceImpl is black box for customer : 
customer only receive cipher text on given keys,
text ( that want to encrypt ) and get decrypted text on given cipher text.
* Generating prime public Exponent with more  
or equals than 10 bits, that  ![equation](https://latex.codecogs.com/gif.download?%5Cgcd%28e%2C%5Cphi%20%28n%29%29%20%3D%201).
 We used so because of Coppersmith's attack on low public Exponent. Also it not recommended to generate large publicE or near ![equation](https://latex.codecogs.com/gif.download?%5C%20%5Cphi%20%28n%29%20/2) 
. For example, assume e = ![equation](https://latex.codecogs.com/gif.download?%5C%20%5Cphi%20%28n%29%20/%202%20+%201), hence for all M satisfy :
 ![equation](https://latex.codecogs.com/gif.download?%5C%20M%5E%7Be%7D%20%5C%20%5Cequiv%20%5C%20M%5E%7B%5Cphi%20%28n%29%20/%202%20+%201%7D%20%5C%20%5Cequiv%20%5C%20M%5E%7B%5Cphi%28n%29/2%7D%20*%20M%20%5C%20%5Cequiv%20%5C%20M%20%5Cmod%20n)


<img src="https://render.githubusercontent.com/render/math?math=%5C%20euler%20%5C%20function%20%5C%20%5Cphi%20(n)%20%3A%20%5Cmathbb%7BN%7D%20%5Crightarrow%20%5Cmathbb%7BN%7D)">

* Correctness of implementation was tested with remote server ( see class Server, ServerService )

# <a name="structure"></a>Tech stack
* Java 8
* Maven 4.0.0
* JUnit 4
* JSON



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