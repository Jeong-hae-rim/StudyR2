


#문제 1 R 코드로 제시된 hotel.txt를 읽고 제일 많이 나온 명사 10개를 
#Java 코드로 전달하여 다음과 같이 Java 코드로 출력한다.


library(KoNLP)

rm(list = ls())

word <- readLines("book/hotel.txt")

word2 <- extractNoun(word)
word3 <- unlist(word2)


word4 <- gsub("[[:punct:]]","",word3)


word4 <- unlist(strsplit(word4, split = "\\s+"))

table_word <- table(word4)

final_word <- sort(table_word, decreasing = T)

head(final_word, 10)


final_word2 <- names(final_word)

final_word3 <- head(final_word2, 10)

final_word3


#[문제 2] R 코드로 제시된 hotel.txt를 읽고 제일 많이 나온 명사 10개를 
#명칭과 횟수로 구성되는 데이터프레임을 생성해서 Java 코드로 전달하여 
#다음과 같이 Java 코드로 출력한다.


library(KoNLP)

rm(list = ls())

word <- readLines("book/hotel.txt")

word2 <- extractNoun(word)
word3 <- unlist(word2)


word4 <- gsub("[[:punct:]]","",word3)


word4 <- unlist(strsplit(word4, split = "\\s+"))

table_word <- table(word4)

final_word <- sort(table_word, decreasing = T)

head(final_word, 10)


final_word2 <- as.data.frame(final_word)

final_word3 <- head(final_word2, 10)

final_word3