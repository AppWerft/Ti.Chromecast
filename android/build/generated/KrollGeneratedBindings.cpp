/* C++ code produced by gperf version 3.0.3 */
/* Command-line: /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/gperf -L C++ -E -t /private/var/folders/25/yvdn9h053sv3htdkzq8558k40000gn/T/fuerst/tichromecast-generated/KrollGeneratedBindings.gperf  */
/* Computed positions: -k'' */

#line 3 "/private/var/folders/25/yvdn9h053sv3htdkzq8558k40000gn/T/fuerst/tichromecast-generated/KrollGeneratedBindings.gperf"


#include <string.h>
#include <v8.h>
#include <KrollBindings.h>

#include "ti.chromecast.TichromecastModule.h"
#include "ti.chromecast.MediaRouteSelectorProxy.h"


#line 14 "/private/var/folders/25/yvdn9h053sv3htdkzq8558k40000gn/T/fuerst/tichromecast-generated/KrollGeneratedBindings.gperf"
struct titanium::bindings::BindEntry;
/* maximum key range = 6, duplicates = 0 */

class TichromecastBindings
{
private:
  static inline unsigned int hash (const char *str, unsigned int len);
public:
  static struct titanium::bindings::BindEntry *lookupGeneratedInit (const char *str, unsigned int len);
};

inline /*ARGSUSED*/
unsigned int
TichromecastBindings::hash (register const char *str, register unsigned int len)
{
  return len;
}

struct titanium::bindings::BindEntry *
TichromecastBindings::lookupGeneratedInit (register const char *str, register unsigned int len)
{
  enum
    {
      TOTAL_KEYWORDS = 2,
      MIN_WORD_LENGTH = 32,
      MAX_WORD_LENGTH = 37,
      MIN_HASH_VALUE = 32,
      MAX_HASH_VALUE = 37
    };

  static struct titanium::bindings::BindEntry wordlist[] =
    {
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""},
      {""}, {""}, {""}, {""}, {""},
#line 16 "/private/var/folders/25/yvdn9h053sv3htdkzq8558k40000gn/T/fuerst/tichromecast-generated/KrollGeneratedBindings.gperf"
      {"ti.chromecast.TichromecastModule", ::ti::chromecast::TichromecastModule::bindProxy, ::ti::chromecast::TichromecastModule::dispose},
      {""}, {""}, {""}, {""},
#line 17 "/private/var/folders/25/yvdn9h053sv3htdkzq8558k40000gn/T/fuerst/tichromecast-generated/KrollGeneratedBindings.gperf"
      {"ti.chromecast.MediaRouteSelectorProxy", ::ti::chromecast::tichromecast::MediaRouteSelectorProxy::bindProxy, ::ti::chromecast::tichromecast::MediaRouteSelectorProxy::dispose}
    };

  if (len <= MAX_WORD_LENGTH && len >= MIN_WORD_LENGTH)
    {
      unsigned int key = hash (str, len);

      if (key <= MAX_HASH_VALUE)
        {
          register const char *s = wordlist[key].name;

          if (*str == *s && !strcmp (str + 1, s + 1))
            return &wordlist[key];
        }
    }
  return 0;
}
#line 18 "/private/var/folders/25/yvdn9h053sv3htdkzq8558k40000gn/T/fuerst/tichromecast-generated/KrollGeneratedBindings.gperf"

