# document-validator

A document-validator é uma ferramenta que facilita a validação de Cpfs, CNPJs e Rgs.

# configuração

De início, acrescente o seguinte trecho no root <b>build.gradle</b>

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Em seguida, você deve adicionar a dependência do projeto no arquivo gradle do módulo desejado

```
dependencies {
    implementation 'com.github.JhoanesFreitas:document-validator:v0.0.2'
}
```

# Uso

Em primeiro lugar, você deve obter uma instância do validador através de um `DocumentValidatorFactory`. Para isso você usará um dos factories abaixo:

* CnpjValidatorFactory
* CpfValidatorFactory
* RgValidatorFactory

Por exemplo, você poderia querer obter um validador de CNPJ.
Assim, você faria:

```
var documentValidatorFactory: DocumentValidatorFactory =
       CnpjValidatorFactory()
    
val cnpjValidator = documentValidatorFactory.getDocumentValidator()
```

Com o CnpjValidator em mãos, você poderá checar se determinado CNPJ é válido ou inválido. A fim de conseguir o resultado desejado poderíamos fazer:

```
cnpjValidator.validate("00.111.222/0001-34") //Este é um CNPJ inválido.
```

ou ainda:

```
cnpjValidator.validate("00111222000134") //Este é um CNPJ inválido.
```

O resultado desta chamada será ou `true` para documento válido ou `false` para documento inválido.

Nota: A validação de RG é baseada nas regras da SSP/SP.


