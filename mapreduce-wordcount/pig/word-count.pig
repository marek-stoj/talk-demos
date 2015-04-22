data = LOAD '/demo/in' as (line: chararray);

words = FOREACH data GENERATE FLATTEN(TOKENIZE(line)) AS word;
words = FOREACH words GENERATE REGEX_EXTRACT(LOWER(word), '([a-z]+)', 1) as word;
grouped = group words by word;
counts = FOREACH grouped GENERATE COUNT(words) as count, group;
counts = ORDER counts BY count ASC;
STORE counts INTO '/demo/out3';
